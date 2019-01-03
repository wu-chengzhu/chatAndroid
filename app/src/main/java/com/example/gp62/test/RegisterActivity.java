package com.example.gp62.test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity  {
    private Button login;
    private Button register;
    private EditText  uid;
    private  EditText pwd;
    private TextView textView;
    private String username;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register=findViewById(R.id.register);
        textView=findViewById(R.id.infor);//提示框
        uid=findViewById(R.id.userId);
        pwd=findViewById(R.id.userPassword);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 sendRequestWithOkHttp();
//                Intent intent=new Intent(MainActivity.this,ClientActivity.class);
//                startActivity(intent);
//                finish();
            }
        });


    }
    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
//                  //  Request request = new Request.Builder().url("http://192.168.32.1:88/Login").build();
                    username=uid.getText().toString().trim();
                    password=pwd.getText().toString().trim();
                    Log.v("test:","用户名"+username+" ");
                    Log.v("test:","用户名"+password+" ");


                    RequestBody requestBody = new FormBody.Builder().add("user",username).
                            add("pass", password).build();

//                     //final String responseData;
                    Request request = new Request.Builder().url("http://192.168.32.1:88/register")
                            .post(requestBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();

                    Gson gson=new GsonBuilder().create();
                    Boolean result=gson.fromJson(responseData,Boolean.class);
                    if(result==false) //用户名
                    {
                        showResponse("注册失败,该用户名已经有了");
                        //return ;
                    }
                    else{
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
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
                textView.setText(response);
            }
        });
    }





}

