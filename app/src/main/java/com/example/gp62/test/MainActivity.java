package com.example.gp62.test;//package com.example.gp62.test;


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

/**
 * 主Activity里面有三个Fragment 是NewFragment MeFragment ContactsFragment
 */
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