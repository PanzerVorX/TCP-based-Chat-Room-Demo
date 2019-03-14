package com.example.socketdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputIP;
    Button loginButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        inputIP=(EditText)findViewById(R.id.ip_edit);
        loginButton=(Button)findViewById(R.id.login_button);

        loginButton.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                String ip=inputIP.getText().toString();
                Intent intent=new Intent(LoginActivity.this,FirstActivity.class);
                intent.putExtra("IP",ip);
                startActivity(intent);
                finish();
                break;
        }
    }
}
