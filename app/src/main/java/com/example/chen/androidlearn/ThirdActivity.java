package com.example.chen.androidlearn;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import service.MyIntentService;
import service.MyService;
import thread.MyThread;

public class ThirdActivity extends AppCompatActivity {

    private Button bThird, bThird2, bThird3, bThird4, bThird5;
    private TextView tThird;
    private MyThread myThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        bThird = findViewById(R.id.bThird);
        bThird.setOnClickListener(toService);
        bThird2 = findViewById(R.id.bThird2);
        bThird2.setOnClickListener(toService);
        bThird3 = findViewById(R.id.bThird3);
        bThird3.setOnClickListener(toService);
        tThird = findViewById(R.id.tThird);
        bThird4 = findViewById(R.id.bThird4);
        bThird4.setOnClickListener(toService);
        bThird5 = findViewById(R.id.bThird5);
        bThird5.setOnClickListener(toService);
        /*启动自己的工作线程*/
        myThread = new MyThread();
        myThread.start();
    }

    View.OnClickListener toService = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == bThird){
                /*启动普通服务类的子类*/
                Intent i = new Intent();
                i.setClass(ThirdActivity.this, MyService.class);
                startService(i);
            }
            if (view == bThird2){
                /*启动IntentService服务子类*/
                Intent i = new Intent();
                i.setClass(ThirdActivity.this, MyIntentService.class);
                startService(i);
            }
            if (view == bThird3){
                /*发送操作为"broastcast"的广播*/
                Intent i = new Intent();
                i.setAction("broastcast");
                sendBroadcast(i);
            }
            if (view == bThird4){
                /*执行异步任务并在其中更新UI*/
                String s[] = {"1", "2", "3"};
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute(s);
            }
            if (view == bThird5){
                /*在主线程中调用工作线程的Handler并向其发送消息*/
                myThread.getHandler().sendEmptyMessage(1);
                myThread.getHandler().sendEmptyMessage(2);
            }

        }
    };
    /*private只能修饰子类即声明此类只能在该类的外部类中使用
    * 内部类可以访问外部类的属性和方法即使为private，但是外部类不能访问内部类的的属性和方法
    * 内部类可以独自实现一个接口而不受外部类是否实现该接口的影响*/
    private class MyAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute(){
            Toast.makeText(ThirdActivity.this, tThird.getText().toString(),Toast.LENGTH_SHORT).show();
        }
        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            for (int i = 0; i < strings.length; i++){
                result += strings[i];
            }
            return result;
        }
        @Override
        protected void onPostExecute(String string){
            tThird.setText(string);
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        /*在Activity不可见时关闭服务*/
        Intent i = new Intent();
        i.setClass(ThirdActivity.this, MyService.class);
        stopService(i);
    }
}
