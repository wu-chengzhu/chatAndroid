package com.example.gp62.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {

    private Button back;
    private SearchView searchV;
    private TextView infor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfriend);
        back = findViewById(R.id.back);
        searchV = findViewById(R.id.search);
        infor=findViewById(R.id.infor);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(SearchActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // 设置搜索文本监听
        searchV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.v("SearchView","onQueryTextSubmit 被调用");
                sfiRequest(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.v("SearchView","onQueryTextChange 被调用");
                return false;
            }


        });
    }

    private void sfiRequest(final String query) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
//                  //  Request request = new Request.Builder().url("http://192.168.32.1:88/Login").build();
                   String searchName=query;
                    RequestBody requestBody = new FormBody.Builder().add("friendName",searchName).build();

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
                        Log.v("result","没有这个用户");
                        showResponse("没有这个用户，查询失败");
                    }
                    else
                    {
                        Log.v("result","有这个用户");
                        Intent intent=new Intent(SearchActivity.this,AddFriendActivity.class);//new 一个intent对象
                        startActivity(intent);//开启一个activity
                        finish();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() { //UI线程上运行
            @Override
            public void run() {
                // Toast.makeText(getBaseContext(),"注册失败，该用户名已经有人注册过了",Toast.LENGTH_LONG);
                infor.setText(response);
            }
        });
    }



}
