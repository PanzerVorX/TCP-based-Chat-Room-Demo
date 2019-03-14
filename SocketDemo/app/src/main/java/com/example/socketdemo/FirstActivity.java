package com.example.socketdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class FirstActivity extends AppCompatActivity {

    private TextView txtshow;
    private EditText editsend;
    private Button btnsend;
    private String HOST = null;
    private static final int PORT = 12345;
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String content = "";
    private StringBuilder sb = null;

    public Handler handler = new Handler() {//使用Handler更新UI
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                sb.append(content);
                txtshow.setText(sb.toString());
                editsend.setText("");
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        sb = new StringBuilder();
        txtshow = (TextView) findViewById(R.id.txtshow);
        editsend = (EditText) findViewById(R.id.editsend);
        btnsend = (Button) findViewById(R.id.btnsend);

        HOST=getIntent().getStringExtra("IP");//获取输入的IP

        //Socket客户端实现互相通信：客户端：正常进行Socket通信，监听服务端发送的数据（监听输入流）
        new receiveThread().start();
        btnsend.setOnClickListener(new View.OnClickListener() {//发送按钮注册点击事件

            public void onClick(View v) {
                new SendThread().start();
            }
        });
    }

    class receiveThread extends Thread{//监听输入流的线程
        public void run() {
            try {
                socket = new Socket(HOST, PORT);
                //获取Socket输入流/输出流的载体并监听输入流
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                //客户端与服务端的Socket通信中，若其中一方有自主下线能力（关闭Socket/Socket对应流），则另一方对Socket的相关操作时需考虑判断Socket/Socket对应流的连接状态
                while (true) {
                    if (socket.isConnected()) {//判断Socket是否连接服务端：Socket的isConnected()
                        if (!socket.isInputShutdown()) {//判断Socket的输入流/输出流是否关闭：Socket的isInputShutdown()/isOutputShutdown()
                            if ((content = in.readLine()) != null) {
                                content += "\n";
                                handler.sendEmptyMessage(0x123);
                            }
                        }
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    class SendThread extends Thread{//发送消息的线程
        String msg = editsend.getText().toString();

        public void run() {

            if (socket.isConnected()) {
                if (!socket.isOutputShutdown()) {
                    out.println(msg);
                }
            }
        }
    }

    class ExitThread extends Thread{//退出服务端的线程
        public void run() {
            try
            {
                if (socket.isConnected()) {
                    if (!socket.isOutputShutdown()) {
                        out.println("bye");
                        socket.shutdownInput();
                        socket.shutdownOutput();
                        socket.close();
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        new ExitThread().start();//活动销毁时通过输出流向服务端发送退出标识，防止活动销毁后，连接客户端计数不随之减少
    }
}