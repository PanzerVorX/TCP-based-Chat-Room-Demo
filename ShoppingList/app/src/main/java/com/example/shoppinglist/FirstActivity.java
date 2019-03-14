package com.example.shoppinglist;

import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    List<Map<String,Object>>dataList=new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        initFruit(dataList);
        ListView listView=(ListView)findViewById(R.id.listview);
        MyAdapter adapter=new MyAdapter(dataList);
        listView.setAdapter(adapter);
        ImageButton countButton=(ImageButton)findViewById(R.id.count_button);
        countButton.setOnClickListener(this);
    }

    public void onClick(View v) {//统计各个控件的显示数据：遍历数据链表中数据实例的成员
        String str="";
        for(int i=0;i<dataList.size();i++){
            if(((CheckBoxBoolean)dataList.get(i).get("cbx")).cbxBoolean){
                str+=(String)dataList.get(i).get("text")+" ";
            }
        }

        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setIcon(R.drawable.alerticon);
        alert.setTitle("购物清单：");
        if (!str.equals(""))
            alert.setMessage("你好，你选择了如下商品："+str);
        else
            alert.setMessage("你未选商品");
        alert.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.create();
        alert.show();
    }

    public void initFruit(List<Map<String,Object>> goodsList){
        Map map1=new HashMap<String,Object>();
        map1.put("image",R.drawable.apple_pic);
        map1.put("text","苹果");
        map1.put("cbx",new CheckBoxBoolean(false));
        map1.put("details","苹果：好吃程度1");
        dataList.add(map1);

        Map map2=new HashMap<String,Object>();
        map2.put("image",R.drawable.banana_pic);
        map2.put("text","香蕉");
        map2.put("cbx",new CheckBoxBoolean(false));
        map2.put("details","香蕉：好吃程度2");
        dataList.add(map2);

        Map map3=new HashMap<String,Object>();
        map3.put("image",R.drawable.orange_pic);
        map3.put("text","橘子");
        map3.put("cbx",new CheckBoxBoolean(false));
        map3.put("details","橘子：好吃程度3");
        dataList.add(map3);

        Map map4=new HashMap<String,Object>();
        map4.put("image",R.drawable.watermelon_pic);
        map4.put("text","西瓜");
        map4.put("cbx",new CheckBoxBoolean(false));
        map4.put("details","西瓜：好吃程度4");
        dataList.add(map4);

        Map map5=new HashMap<String,Object>();
        map5.put("image",R.drawable.pear_pic);
        map5.put("text","梨子");
        map5.put("cbx",new CheckBoxBoolean(false));
        map5.put("details","梨子：好吃程度5");
        dataList.add(map5);

        Map map6=new HashMap<String,Object>();
        map6.put("image",R.drawable.grape_pic);
        map6.put("text","葡萄");
        map6.put("cbx",new CheckBoxBoolean(false));
        map6.put("details","葡萄：好吃程度6");
        dataList.add(map6);


        Map map7=new HashMap<String,Object>();
        map7.put("image",R.drawable.pineapple_pic);
        map7.put("text","菠萝");
        map7.put("cbx",new CheckBoxBoolean(false));
        map7.put("details","菠萝：好吃程度7");
        dataList.add(map7);


        Map map8=new HashMap<String,Object>();
        map8.put("image",R.drawable.strawberry_pic);
        map8.put("text","草莓");
        map8.put("cbx",new CheckBoxBoolean(false));
        map8.put("details","草莓：好吃程度8");
        dataList.add(map8);

        Map map9=new HashMap<String,Object>();
        map9.put("image",R.drawable.cherry_pic);
        map9.put("text","樱桃");
        map9.put("cbx",new CheckBoxBoolean(false));
        map9.put("details","樱桃：好吃程度9");
        dataList.add(map9);

        Map map10=new HashMap<String,Object>();
        map10.put("image",R.drawable.mango_pic);
        map10.put("text","芒果");
        map10.put("cbx",new CheckBoxBoolean(false));
        map10.put("details","芒果：好吃程度10");
        dataList.add(map10);
    }
}
