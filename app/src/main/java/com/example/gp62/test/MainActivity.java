package com.example.gp62.test;//package com.example.gp62.test;
//
//import android.content.Intent;
//import android.os.Handler;
//import android.os.Message;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.Headers;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//public class MainActivity extends AppCompatActivity {
//    private Button login;
//    private EditText  uid;
//    private  EditText pwd;
//    private TextView textView;
//    private String username;
//    private String password;
//    public static final int SHOW_RESPONSE=1;
//
////    public Handler handler=new Handler() {
////        public void handleMessage(Message msg)
////        {
////            switch (msg.what){
////                case SHOW_RESPONSE:
////                    String response=(String)msg.obj;
////                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
////                    break;
////                default:
////                    break;
////            }
////        }
////    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        login=findViewById(R.id.signIn);
//        textView=findViewById(R.id.infor);//提示框
//        uid=findViewById(R.id.userId);
//         pwd=findViewById(R.id.userPassword);
//
//
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendRequestWithOkHttp();
////                Intent intent=new Intent(MainActivity.this,ClientActivity.class);
////                startActivity(intent);
////                finish();
//            }
//        });
//    }
//
////    private void sendRequestWithOkHttp() {
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
////                try {
////                    OkHttpClient client = new OkHttpClient();//建立一个OKhttpClient对象
//////                    Map<String,String> map=new HashMap<>();
//////                    map.put("user","wcz");
//////                    map.put("pass","123");
//////                    Gson gson=new GsonBuilder().enableComplexMapKeySerialization().create();//生成的Gson对象能够让map序列化
//////                    String json=gson.toJson(map);//把map转成json数据类型
////                   // final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
////                  //  RequestBody body=RequestBody.create(JSON,json);//创建一个请求对象，里面包含json数据类型的数据
////
////                    Request request = new Request.Builder().url("http://192.168.43.98:88/Login").build(); //建立连接
////                          //  .post(body)//把数据传给服务器
////
////                    Response response = client.newCall(request).execute();//获得返回的请求对象
////                    String responseData = response.body().string();//获得身体部分的信息
////                   // Boolean result= gson.fromJson(responseData,Boolean.class);
////                    showResponse(responseData+"");
////
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
////            }
////        }).start();
////    }
//
//
//    private void sendRequestWithOkHttp() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient client = new OkHttpClient();
////                  //  Request request = new Request.Builder().url("http://192.168.32.1:88/Login").build();
//                    username=uid.getText().toString().trim();
//                    password=pwd.getText().toString().trim();
//                    Log.v("test:","用户名"+username+" ");
//                    Log.v("test:","用户名"+password+" ");
//                    Map<String,String> map=new HashMap<>();
//                    map.put("user",username);
//                    map.put("pass",password);
//                    Gson gson=new GsonBuilder().enableComplexMapKeySerialization().create();//生成的Gson对象能够让map序列化
//                    String json=gson.toJson(map);//把map转成json数据类型
//                    final MediaType JSON = MediaType.parse("application/json; charset=utf-8");//描述请求/响应的内容类型
//                 RequestBody body=RequestBody.create(JSON,json);//创建一个请求对象，里面包含json数据类型的数据
////
////                     //final String responseData;
//                    Request request = new Request.Builder().url("http://192.168.32.1:88/Login")
//                             .post(body)//把数据传给服务器
//                            .build();
//
//                    Response response = client.newCall(request).execute();
//                    String responseData = response.body().string();
//
//                    String result=gson.fromJson(responseData,String.class);
//                    if(result.equals("false")) //用户名
//                    {
//                        showResponse("用户名或密码输入错误，请重新输入。");
//                    }
//                    else{
//                Intent intent=new Intent(MainActivity.this,ClientActivity.class);
//                startActivity(intent);
//                finish();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//
//
//
//
//
//
//
//    private void showResponse(final String response) {
//        runOnUiThread(new Runnable() { //UI线程上运行
//            @Override
//            public void run() {
//                textView.setText(response);
//            }
//        });
//    }
//
//
////    private boolean loginPro(){
////        //获取用户输入的用户名和密码
////       String username= uid.getText().toString();
////        String password=pwd.getText().toString();
////        Gson gson=new Gson(); //new 一个Gson对象
////
////        try{
////            String data=query(username,password);
////            Log.v("service","执行到智利");
////            if(gson.fromJson(data,Integer.class)>0)//如果传回来的这个数大于0
////            {
////                return true;//返回true
////
////            }
////        }
////        catch (Exception e)
////        {
////            Toast.makeText(MainActivity.this,"服务器响应异常，请重试",Toast.LENGTH_SHORT);
////            e.printStackTrace();
////        }
////        return false;//没有这个用户
////
////
////    }
//
////
//
////    public void SendByHttpClient(final String id,final String pw){
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
////                try{
////                    HttpClient httpclient=new DefaultHttpClient();
////                    HttpPost httpPost=new HttpPost("http://192.168.43.98:88/Login");//服务器地址，指向Servlet
////                    //将传递的参数进行封装
////                    List<NameValuePair> params=new ArrayList<NameValuePair>();//将id和pw装入list
////                    params.add(new BasicNameValuePair("ID",id));
////                    params.add(new BasicNameValuePair("PW",pw));
////                    final UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params,"utf-8");//以UTF-8格式发送
////                    httpPost.setEntity(entity);//设置请求参数
////                    HttpResponse httpResponse= httpclient.execute(httpPost);//发送请求 返回httpResponse
////                    if(httpResponse.getStatusLine().getStatusCode()==200)//在200毫秒之内接收到返回值
////                    {
////                        HttpEntity entity1=httpResponse.getEntity();//得到服务器的响应内容的对象
////                        String response=EntityUtils.toString(entity1, "utf-8");//以UTF-8格式解析
////                        Message message=new Message();
////                        message.what=SHOW_RESPONSE;
////                        message.obj=response;
////                        handler.sendMessage(message);//使用Message传递消息给线程
////                    }
////
////                }
////                catch (Exception e)
////                {
////                    e.printStackTrace();
////                }
////            }
////        }).start();//开个线程
////    }
//
//}

import com.example.gp62.test.Fragment.ContactsFragment;
import com.example.gp62.test.Fragment.MeFragment;
import com.example.gp62.test.Fragment.NewsFragment;


import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {
    private  Gson gson;
    private TextView chat;
    private Toolbar toolbar;
    private Fragment newsfragment;
    private Fragment contactsfragment;
    private Fragment mefragment;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    replaceFragment(newsfragment);
                    return true;
                case R.id.navigation_contacts:
                    replaceFragment(contactsfragment);
                    return true;
                case R.id.navigation_me:
                    replaceFragment(mefragment);
                    return true;
            }
            return false;
        }
    };







    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsfragment=new NewsFragment();
        replaceFragment(newsfragment);
        toolbar=findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        contactsfragment=new ContactsFragment();
        mefragment=new MeFragment();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




//         chat=findViewById(R.id.chat);
//        chat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,MessageActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);//设置菜单的布局
        return super.onCreateOptionsMenu(menu);//调用父类的方法创造这个菜单
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add:
               Intent intent=new Intent(MainActivity.this,SearchActivity.class);
               startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }




    public  void replaceFragment(Fragment fragment)
    {
        //开启事务，fragment的控制是由事务来实现的
        FragmentManager ft=getSupportFragmentManager();
        FragmentTransaction ftr=ft.beginTransaction();
        ftr.replace(R.id.root,fragment);
        ftr.commit();
    }






//    private void sendRequestWithOkHttp() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//
//                    while(!Thread.interrupted()) {
//
//
//                        OkHttpClient client = new OkHttpClient();
//                        RequestBody requestBody = new FormBody.Builder().add("selfId", LoginActivity.id + "").build();
//
////                     //final String responseData;
//                        Request request = new Request.Builder().url("http://192.168.32.1:88/message")
//                                .post(requestBody)
//                                .build();
//
//
//                        Response response = client.newCall(request).execute();
//                        String responseData = response.body().string();
//
//                        gson = new Gson();
//
//                        // ArrayList<Message> msgList = new ArrayList<Message>();
//                        String s;
//                        ArrayList<Message> amsg = gson.fromJson(responseData, new TypeToken<ArrayList<Message>>() {
//                        }.getType());
//                        for (int i = 0; i < amsg.size(); i++) {
//                            Message msg = amsg.get(i);//得到消息中的一个
//                            Log.v("message", msg.toString());//
//
//                            s = "发送者：" + msg.getSendName() + "msg:" + msg.getMsg();
//                            showResponse(s);
//                        }
//
//
////                    if(result.equals("false")) //用户名
////                    {
////                        showResponse("用户名或密码输入错误，请重新输入。");
////                    }
////                    else{
////                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
////                        startActivity(intent);
////                        finish();
////                    }
//                        Thread.sleep(2000);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//
//    private void showResponse(final String response) {
//        runOnUiThread(new Runnable() { //UI线程上运行
//            @Override
//            public void run() {
//                textView.setText(response);
//            }
//        });
//    }


}