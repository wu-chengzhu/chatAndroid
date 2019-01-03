package com.example.gp62.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gp62.test.Entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class AddFriendActivity extends AppCompatActivity {
   private TextView userName;
   private  TextView userID;
    private  Gson gson;
    private Button add;
    private  Button back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendinfor);
        userName = findViewById(R.id.userName);
        userID = findViewById(R.id.userId);
        add=findViewById(R.id.add);
        back =findViewById(R.id.back);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    OkHttpClient client = new OkHttpClient();//建造一个对象
                    RequestBody requestBody = new FormBody.Builder().add("userid", LoginActivity.id+"").build();
                    Request request = new Request.Builder().url("http://192.168.32.1:88/addfriend")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    System.out.print(responseData);
                    gson = new Gson();
                    String s;

                    User friend = gson.fromJson(responseData, User.class);
                    int userid = friend.getId();
                    String username = friend.getUsername();
                    showResponse(userid, username);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddFriendActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRequest();
            }
        });




    }


    private void addRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
//                  //  Request request = new Request.Builder().url("http://192.168.32.1:88/Login").build();
                    RequestBody requestBody = new FormBody.Builder().add("action","add").build();

//                     //final String responseData;
                    Request request = new Request.Builder().url("http://192.168.32.1:88/addfriend")
                            .post(requestBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    //Log.v("result","fdfd"+responseData);
                    Gson gson=new GsonBuilder().create();;//创建一个Gson对象

                    Boolean searchResult=gson.fromJson(responseData,Boolean.class);
                    if(searchResult==false)
                    {
                      //  System.out.print("加好友失败");
                     //   Log.v("a","没有这个用户");
                        System.out.println("加好友失败");
                       showAddResult("加好友失败");
                    }
                    else
                    {
                        Log.v("result","有这个用户");
                        Intent intent=new Intent(AddFriendActivity.this,MainActivity.class);//new 一个intent对象
                        startActivity(intent);//开启一个activity
                        finish();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }





    private void showAddResult(final String response) {
        runOnUiThread(new Runnable() { //UI线程上运行
            @Override
            public void run() {
                 Toast.makeText(getBaseContext(),"加好友失败",Toast.LENGTH_LONG);

            }
        });
    }


    private void showResponse(final int id,final String name) {
            runOnUiThread(new Runnable() { //UI线程上运行
                @Override
                public void run() {
                   userID.setText(id+"");
                   userName.setText(name);
                }
            });
        }

    }

