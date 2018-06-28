package thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Created by chen on 2018/6/6.
 */

public class MyThread extends Thread {
    private Handler handler;

    public MyThread(){
    }
    public void run(){
        Log.e("线程", "启动了");
        /*为当前线程绑定Looper对象即为当前线程绑定一个消息队列
        * Looper用于管理一个线程的消息循环，除主线程外线程默认无消息队列所以需要创建Looper对象为线程绑定消息队列
        * Looper.prepare()方法用于创建Looper对象并且保证每个线程最多只有一个Looper对象*/
        Looper.prepare();
        /*Handler用于进行消息传递和处理
        *每个Handler实例都与创建它的线程和线程中的消息队列绑定
        *重写Hander的处理消息回调方法以便当该Handler中的消息队列中存在消息时对消息进行处理*/
        handler = new Handler(){
            @Override
            public void handleMessage (Message msg){
                if (msg.what == 1){
                    Log.e("msg1:","1");
                }
                if (msg.what == 2){
                    Log.e("msg2:","2");
                }
            }
        };
        /*Looper.loop()方法使用一个for死循环来不断的从消息队列中取出消息并将消息传给消息所对应的Handler处理
        * 如果消息队列中没有可获取的消息则阻塞*/
        Looper.loop();
    }
    public Handler getHandler(){
        return this.handler;
    }
}
