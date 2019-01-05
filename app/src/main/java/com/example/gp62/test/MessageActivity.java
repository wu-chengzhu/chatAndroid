package com.example.gp62.test;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gp62.test.Adapter.MsgAdapter;
import com.example.gp62.test.Entity.Message;
import com.example.gp62.test.Entity.Msg;
import com.example.gp62.test.Fragment.NewsFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 聊天界面
 */
public class MessageActivity extends AppCompatActivity {
        public static List<Msg> msgList=new ArrayList<>();
        private EditText inputText;
        private Button send;
        public static RecyclerView msgRecyclerView;
        public static MsgAdapter adapter;
         public static Handler  handler=new Handler() {
        public void handleMessage(android.os.Message msg)
        {//消息接收处理
           if(msg.what==0x123)
           {
               Bundle bundle=msg.getData();
               String message=bundle.getString("msg");
               msgList.add(new Msg(message,Msg.TYPE_RECEIVED));
        adapter.notifyItemInserted(MessageActivity.msgList.size()-1);//当有新消息时，刷新ListView中的显示
        msgRecyclerView.scrollToPosition(MessageActivity.msgList.size()-1);//将ListView定位到最后一行
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        initMsgs();//初始化消息数据
        inputText=findViewById(R.id.input_text);
        send=findViewById(R.id.send);
        msgRecyclerView=(RecyclerView)findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(msgList);//new 一个adapter 并给它数据
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String content=inputText.getText().toString();//从输入框得到内容
                if(!"".equals(content))//不为空消息
                {
                    Msg msg=new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);//消息显示数组中添加这个消息
                    adapter.notifyItemInserted(MessageActivity.msgList.size()-1);//当有新消息时，刷新ListView中的显示
                    msgRecyclerView.scrollToPosition(MessageActivity.msgList.size()-1);//将ListView定位到最后一行
                    inputText.setText("");//清空输入框中的内容
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                OkHttpClient client = new OkHttpClient();//建立一个okHttpClient对象
                                RequestBody requestBody;
                                if(LoginActivity.id!=2)
                                {
                                    requestBody= new FormBody.Builder().add("sender",""+LoginActivity.id).
                                            add("receiver",2+"").add("msg",content).build();
                                }
                                else
                                {
                                    requestBody = new FormBody.Builder().add("sender",""+LoginActivity.id).
                                            add("receiver",1+"").add("msg",content).build();
                                }

                                //final String responseData;
                                Request request = new Request.Builder().url("http://192.168.32.1:88/message")
                                        .post(requestBody)
                                        .build();
                                Response response = client.newCall(request).execute();
                                String responseData = response.body().string();
                                Log.v("message","发送成功："+responseData);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();







                }
            }
        });
    }

    private void initMsgs()
    {
        Msg msg1=new Msg("Hello guy",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2=new Msg("Hello what's up?",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3=new Msg("This is Tom.",Msg.TYPE_RECEIVED);
        msgList.add(msg3);

    }
}
