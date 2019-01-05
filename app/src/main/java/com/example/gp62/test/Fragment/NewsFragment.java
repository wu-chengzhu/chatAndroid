package com.example.gp62.test.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gp62.test.Adapter.MySimpleAdapter;
import com.example.gp62.test.Entity.Friend;
import com.example.gp62.test.Entity.MessageList;
import com.example.gp62.test.Entity.Msg;
import com.example.gp62.test.LoginActivity;
import com.example.gp62.test.Entity.Message;
import com.example.gp62.test.MessageActivity;
import com.example.gp62.test.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.gp62.test.MessageActivity.adapter;
import static com.example.gp62.test.MessageActivity.msgRecyclerView;

/**
 * 消息列表的Fragment
 */
public class NewsFragment extends Fragment {

    private ListView listView;
    private List<Map<String, Object>> data; //数据源
    private ArrayList<MessageList> messageLists=new ArrayList<MessageList>();//消息列表
    private Map<String, Object> item;
    // private  int image[]={R.drawable.dir,R.drawable.file};
    static public ArrayList<Message> amsg;//接收消息的数组
    private String friend[] = {"wcz", "xm"};
    private String msgA[] = {"hello", "你收到了吗"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news, container, false);
        //Button button = (Button) view.findViewById(R.id.button);

       // handleMessage(amsg);




        Log.v("hello", "meFragment create");

        listView = view.findViewById(R.id.news_listView);

       data = new ArrayList<Map<String, Object>>();

        for (int i = 0; i <2; i++) { //每一个item对应List中一行
//            Message msg=new Message();
//            msg=amsg.get(i);
            item = new HashMap<String, Object>();
          Log.v("show",amsg.toString());
            item.put("friendname", friend[i]); //键名就是发送的人 键值就是发送的消息，
            item.put("msg",msgA[i]);
          data.add(item);
        }

        //生成简单设配器
        MySimpleAdapter listAdapter = new MySimpleAdapter(getContext(), data, R.layout.news_listview,
                new String[]{"friendname", "msg"},
                new int[]{R.id.friendname, R.id.msg});


        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent=new Intent(getContext(),MessageActivity.class);
               startActivity(intent);
            }
        });

        return view;
    }

//    private void handleMessage(ArrayList<Message> msg) {
//
//        Collections.sort(msg);//对所有消息进行排序
//
//        for(int i=0;i<msg.size();i++)
//        {
//            Message message;
//            if(i==0)
//            {
//                message=msg.get(0);
//                int temp=message.getSender();
//                Friend friend=new Friend(message.getSender(),message.getSendName());
//                MessageList messageList=new MessageList(friend,message.getMsg());
//            }
//
//            message=msg.get(i);
//
//            if(temp==)
//            String sendName=message.getSendName();
//            Friend friend=new Friend(message.getSender(),message.getSendName());
//
//            MessageList messageList=new MessageList(friend,)
//
//         //   String smsg=message.getMsg();
//
//        }
//
//
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!Thread.interrupted()) {
                        OkHttpClient client = new OkHttpClient();//new一个OkHttpClient对象用来建立请求的连接
                        //设置请求主体的参把它自己的id作为参数发过去
                        RequestBody requestBody = new FormBody.Builder().add("selfId", LoginActivity.id + "").build();
                        Request request = new Request.Builder().url("http://192.168.32.1:88/message")
                                .post(requestBody)
                                .build();//想消息servlet发送请求
                        Response response = client.newCall(request).execute();//拿到响应的对象
                        String responseData = response.body().string();//拿出json格式数据
                        Gson gson = new Gson();//new一个Gson对象
                        String s;
                         amsg = gson.fromJson(responseData, new TypeToken<ArrayList<Message>>() {}.getType());//取出消息的数据
                        for (int i = 0; i < amsg.size(); i++) {//遍历消息的list
                            Message msg = amsg.get(i);//得到消息中的一个
                            Log.v("message", msg.toString());//
                            android.os.Message message=new android.os.Message();//建一个在andorid中发送消息的对象
                            message.what=0x123;//设置消息的类型
                            Bundle bundle=new Bundle();//new Bundle
                            bundle.putString("msg",msg.getMsg());//把消息放入篮子
                            message.setData(bundle);//传递消息的把bundle设置为数据
                            MessageActivity.handler.sendMessage(message);//向MessageActivity发送数据
                        }
                        Thread.sleep(2000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }






}


