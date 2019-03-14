package com.example.shoppinglist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {

    List<Map<String,Object>> dataList;
    String countGoods;

    public  MyAdapter(List<Map<String,Object>> dataList){
        this.dataList=dataList;
    }

    public int getCount() {
        return dataList.size();
    }

    public Object getItem(int position) {
        return dataList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        Log.d("AAA","getView"+position+" "+convertView);
        final Map map=dataList.get(position);
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
            viewHolder.imageView=convertView.findViewById(R.id.fruit_image);
            viewHolder.textView=convertView.findViewById(R.id.fruit_name);
            viewHolder.checkBox=convertView.findViewById(R.id.chekbox);
            viewHolder.button=convertView.findViewById(R.id.details_button);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder)convertView.getTag();
        }

        viewHolder.imageView.setImageResource((int)map.get("image"));
        viewHolder.textView.setText((String)map.get("text"));

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {//只能在子项每次滑进屏幕时调用的方法中获取当前子项位置

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ((CheckBoxBoolean)(map.get("cbx"))).cbxBoolean=isChecked;
            }
        });
        viewHolder.checkBox.setChecked(((CheckBoxBoolean)map.get("cbx")).cbxBoolean);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("AAA",position+" ");
                AlertDialog.Builder alert= new AlertDialog.Builder(parent.getContext());
                alert.setTitle("物品详情："+(String)(dataList.get(position).get("text")));
                alert.setIcon(R.drawable.alerticon);
                alert.setMessage((String)(dataList.get(position).get("details")));
                alert.setPositiveButton("确定：", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.create();
                alert.show();
            }
        });


        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView;
        CheckBox checkBox;
        Button button;
    }
}
