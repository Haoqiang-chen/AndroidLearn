package service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }
    @Override
    public void onCreate(){
        Toast.makeText(this,"Service创建",Toast.LENGTH_SHORT).show();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Toast.makeText(this,"Service启动了",Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
    @Override
    public void onDestroy(){
        Toast.makeText(this,"Service关闭了",Toast.LENGTH_SHORT).show();
    }
}
