package com.snail.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service {

    private static final String LYJ_TAG = "LYJ_AIDLService";
    private ArrayList<Book> books = new ArrayList<>();

    public AIDLService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LYJ_TAG, "AIDLService onBind");
        return new RemoteServiceImpl();
    }

    //服务端实现AIDL接口方法
    class RemoteServiceImpl extends IRemoteService.Stub {
        @Override
        public int getPid() throws RemoteException {
            int pid = Process.myPid();
            Log.d(LYJ_TAG, "RemoteServiceImpl pid=" + pid);
            return pid;
        }

        @Override
        public void add(Book book) throws RemoteException {
            Log.d(LYJ_TAG, "RemoteServiceImpl add book");
            books.add(book);
        }

        @Override
        public List<Book> get() throws RemoteException {
            Log.d(LYJ_TAG, "RemoteServiceImpl get book");
            return books;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    }

}
