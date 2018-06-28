package com.example.chen.androidlearn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private Button bSecond, bChooser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        bSecond = findViewById(R.id.toMain);
        bSecond.setOnClickListener(toThird);
        bChooser = findViewById(R.id.chooser);
        bChooser.setOnClickListener(toThird);
        /*从上一个Activity中获取传输过来的数据*/
        Intent formMainIntent = getIntent();
        String strFromMain = formMainIntent.getStringExtra("Main");
        if (strFromMain != null)
            Toast.makeText(SecondActivity.this,strFromMain,Toast.LENGTH_SHORT).show();

    }

    View.OnClickListener toThird = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == bSecond){
                /*采用隐式Intent启动组件时Android会将CATEGORY_DEFAULT类别应用于传递给启动Activity方法的隐式Intent对象
                * 所以在采用隐式启动Activity时相应组件要想响应隐式Intent则必须在组件的过滤器中声明android.intent.category.DEFAULT类别
                * 隐式Intent启动Activity时将从操作、类别和数据三个方面将隐式Intent于Intent过滤器相比较：
                * 对于操作（cation）：若过滤器中含有Intent中所有的操作即可匹配成功
                * 对于类别（category）：若过滤器中包含Intent中所有的类别即可匹配成功
                * 对于数据（data）：根据是MIME类型和URI格式进行匹配，
                *    若过滤器中均不包含MIME和URI则不包含MIME和URI的Intent会通过测试
                *    若过滤器中包含MIME或者URI之一时，响应的只包含相同MIME或者相同URI的Intent会通过测试
                *    若过滤器包含MIME和URI，则Intent先进行MIME匹配测试，若通过则进行URI匹配测试*/
                /*采用隐式Intent来启动ThirdActivity*/
                Intent i = new Intent();
                i.setAction("Third");
                i.setAction("Third1");
                i.addCategory("ThirdCategory");
                /*使用resolveActivity来判断是否有Activity能处理该隐式Intent*/
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivity(i);
                }
                else {
                    Toast.makeText(SecondActivity.this," 未找到匹配的Intent过滤器",Toast.LENGTH_SHORT).show();
                }
            }
            if (view == bChooser){
                /*使用隐式Intent时如果有多个应用响应则会向用户提供选择器对话框
                * 用户若想今后一直使用某一款应用来响应某项操作则使用系统提供选择器对话框即可
                * 若用户想今后一直选择不同应用来响应某项操作则应该显示的提供选择器对话框
                * 即使用Intent,createChooser()来创建可以显示启动选择器对话框的Intent*/
                Intent i = new Intent();
                i.setAction("android.intent.action.MAIN");
                i.addCategory("android.intent.category.LAUNCHER");
                Intent chooser = Intent.createChooser(i, "自定义的选择器标题");
                startActivity(chooser);
            }
        }
    };
    /*setResult的返回码一直为0*/
    @Override
    protected void onPause() {
        super.onPause();
        Intent isecond = new Intent();
        isecond.putExtra("Second", "从SecoondActivity来的数据");
        /*将需要返回的数据放入Intent中并使用以下方法将Intent对象返回到上一个Activity的onActivityResult中
        * 第一个参数为标识子Activity的标识码，第二个参数为携带者数据的Intent对象*/
        setResult(Activity.RESULT_OK, isecond);
        Toast.makeText(SecondActivity.this,"hhhh",Toast.LENGTH_SHORT).show();
    }
}
