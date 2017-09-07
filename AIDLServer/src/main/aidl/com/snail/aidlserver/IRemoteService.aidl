// IRemoteService.aidl
package com.snail.aidlserver;

// Declare any non-default types here with import statements
import com.snail.aidlserver.Book;

interface IRemoteService {

    //获取其他进程的pid
    int getPid();
    //向服务端添加数据
    void add(in Book book);

    //获取服务端数据
    List<Book> get();
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);


}
