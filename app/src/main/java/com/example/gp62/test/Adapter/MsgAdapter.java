package com.example.gp62.test.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gp62.test.Entity.Msg;
import com.example.gp62.test.R;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder>
{

    private  List<Msg> mMsgList; //数据源
     static public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
        public ViewHolder(View view) {//获取到子项的最外层布局的中的view
            super(view);
            leftLayout=(LinearLayout)view.findViewById(R.id.left_layout);
            rightLayout=(LinearLayout)view.findViewById(R.id.right_layout);
            leftMsg=(TextView)view.findViewById(R.id.msg_left);
            rightMsg=(TextView)view.findViewById(R.id.msg_right);
        }
    }

    public MsgAdapter(List<Msg> msgList)
    {
        mMsgList=msgList;
    }


    /**
     * 创建ViewHolder实例 把每一项的布局加载进来  然后把布局传到构造函数中去
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        return new ViewHolder(view);//返回这个对象
    }

    /**
     * 对子项的数据进行赋值
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MsgAdapter.ViewHolder holder, int position) {
        Msg msg=mMsgList.get(position);
        if(msg.getType()==Msg.TYPE_RECEIVED)
        //如果是收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
        {
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
        }
        else if(msg.getType()==Msg.TYPE_SENT)
        {//如果是发出的消息，则显示右边的消息布局，将左边的消息布局隐藏
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());
            System.out.println("msg content"+msg.getContent());
        }
    }

    /**
     * 告诉一共有多少子项
     * @return
     */
    @Override
    public int getItemCount() {
        return mMsgList.size();
    }


}
