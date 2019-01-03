package com.example.gp62.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gp62.test.R;

import java.util.List;
import java.util.Map;

public class MySimpleAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String,Object>> data;//数据源
    private int res_layout;//布局文件对应的ID
    private String[] from;//索引
    private int[] to;//对应组件ID
    private LayoutInflater inflater;//用于动态载入界面的inflater

    public MySimpleAdapter(Context context,List<Map<String,Object>> data,int res_layout,String[] from,int[] to){
        this.context=context;
        this.data=data;
        this.res_layout=res_layout;
        this.from=from;
        this.to=to;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount(){
        return data.size();
    }
    @Override
    public Object getItem(int position){
        return  data.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(res_layout,null);
        Map<String,Object>item=(Map<String,Object>)getItem(position);
        //为每一个控件设置显示效果
        for(int i=0;i<to.length;i++)
        {
            //得到需要设置的控件
            View view=convertView.findViewById(to[i]);

                TextView textView=(TextView)view;
                textView.setText((String)item.get(from[i]));

        }
//        Button button=convertView.findViewById(R.id.confirm);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"第"+position+"行按键被点击",Toast.LENGTH_SHORT).show();
//            }
//        });
//


        return convertView;
    }
}
