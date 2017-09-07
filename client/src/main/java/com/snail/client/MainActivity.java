package com.snail.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.snail.aidlserver.Book;
import com.snail.aidlserver.IRemoteService;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String LYJ_TAG = "LYJ_MainActivity";
    private IRemoteService remoteService;
    private MyServiceConnection myServiceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myServiceConnection = new MyServiceConnection();
        //客户端绑定服务端的AIDLService
        //注意5.0以后不支持隐式启动service需要严格制定启动应用的包名和启动Service的完整类名
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.snail.aidldemo", "com.snail.aidlserver.AIDLService"));
        bindService(intent, myServiceConnection, Context.BIND_AUTO_CREATE);
        Log.d(LYJ_TAG, "绑定service操作发出");
    }

    public void doClick(View view) {
        Log.d(LYJ_TAG, "onClick");
        try {
            int pid = remoteService.getPid();
            Log.d(LYJ_TAG, "Client get Server pid=" + pid);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Book book = new Book();
        book.setBookName("Android 开发艺术探索");
        book.setPrice(35);
        try {
            remoteService.add(book);
            Log.d(LYJ_TAG, "client write data to server");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            List<Book> books = remoteService.get();
            Log.d(LYJ_TAG, "client get server data data=" + books.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            //将服务端的binder传递过来再客户端构建IRemoteService从而实现通信
            remoteService = IRemoteService.Stub.asInterface(binder);
            Toast.makeText(MainActivity.this, "客户端绑定服务端成功!", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myServiceConnection != null) {
            unbindService(myServiceConnection);
        }
    }
}
