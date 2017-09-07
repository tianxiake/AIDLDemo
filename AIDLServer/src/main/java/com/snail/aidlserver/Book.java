package com.snail.aidlserver;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yongjie on 2017/9/7.
 */

public class Book implements Parcelable {
    private int price;
    private String bookName;

    public Book() {
    }

    public Book(Parcel in) {
        this.price = in.readInt();
        this.bookName = in.readString();
    }

    public int getPrice() {
        return price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public static Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Book{" +
                "price=" + price +
                ", bookName='" + bookName + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(price);
        out.writeString(bookName);
    }
}
