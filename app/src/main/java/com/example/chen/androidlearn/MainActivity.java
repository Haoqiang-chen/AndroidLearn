package com.example.chen.androidlearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button bSecond, bSForResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bSecond = findViewById(R.id.button); //不用强制转型为Button类型
        bSecond.setOnClickListener(toSecond);
        bSForResult = findViewById(R.id.bSForResult);
        bSForResult.setOnClickListener(toSecond);

    }

    View.OnClickListener toSecond = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if (view == bSecond ){
                /*Intent是信息传递对象用于不同组件之间的通信
                * Intent主要用于以下三个方面：启动Activity、启动或者绑定服务、用于发送广播
                * Intent分为显式Intent和隐式Intent两种：显式即完全限定要被启动的组件的名称，而隐式Intent则在Intent对象中声明要执行的操作从而允许其他应用来处理
                * 使用Intent启动服务时为了安全应使用显示的启动方式*/
                /*Intent显示启动Activity*/
                Intent i = new Intent();
                i.setClass(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
            if (view == bSForResult){
                /*将数据放入Intent并从第二个Activity返回值*/
                Intent i = new Intent();
                i.putExtra("Main", "从MainActivity来的数据");
                i.setClass(MainActivity.this, SecondActivity.class);
                //用该方法从被启动的Activity中获取结果，第一个参数为Intent对象，第二个参数为用于标识当前Activity的标识码
                startActivityForResult(i,123);
            }

        }
    };
    /*重写onActivityResult方法以便获取从子Activity中返回的数据*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 123){
            Toast.makeText(MainActivity.this,"uuuu",Toast.LENGTH_SHORT).show();
            if (resultCode == RESULT_OK){
                String strByGet = data.getStringExtra("Second");
                Toast.makeText(MainActivity.this, strByGet, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
