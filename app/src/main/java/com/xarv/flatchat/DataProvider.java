package com.xarv.flatchat;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;


public class DataProvider extends ContentProvider {
    private DBHelper dbHelper;
    private UriMatcher mUrimatcher;
    private String authorities= "com.xarv.flatchat";

    private final static int GET_MESSAGES = 1;
    private Uri msgTable = Uri.parse("content://" + authorities+"/content"  + "/MSG_TABLE");

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        boolean successInsert ;
        successInsert = dbHelper.insertMsgTable("1","1","Hello, how are you ?","0","default");
        successInsert = dbHelper.insertMsgTable("2", "2", "http://media.mediatemple.netdna-cdn.com/wp-content/uploads/2013/01/3.jpg", "1", "default");
        successInsert = dbHelper.insertMsgTable("3", "3", "How is weather ?", "0", "default");
        successInsert = dbHelper.insertMsgTable("4", "4", "http://media.mediatemple.netdna-cdn.com/wp-content/uploads/2013/01/3.jpg", "1", "default");
        successInsert = dbHelper.insertMsgTable("5", "5", "http://media.mediatemple.netdna-cdn.com/wp-content/uploads/2013/01/3.jpg", "1", "default");
        successInsert = dbHelper.insertMsgTable("6", "6", "Tomorrow is sunday", "0", "default");
        successInsert = dbHelper.insertMsgTable("7", "7", "To define one such view, you need to specify it an Android Context. This is usually the Activity where the tabs will be displayed. Supposing that you initialize your tabs in an Activity, simply pass the Activity instance as a Context", "0", "default");
        successInsert = dbHelper.insertMsgTable("8", "8", "http://media.mediatemple.netdna-cdn.com/wp-content/uploads/2013/01/3.jpg", "1", "default");
        mUrimatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUrimatcher.addURI( authorities,"/content"  + "/MSG_TABLE",1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {


        Cursor cursor = dbHelper.getMessages();

//        switch (mUrimatcher.match(uri)) {
//            case GET_MESSAGES:
//                Cursor cursor = dbHelper.getReadableDatabase().query("MSG_TABLE", projection,
//                null, null, null, null, null);
//                return cursor;
//        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //这里不同于使用观察者模式，只要操作数据库即可，不用关心URI 。
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }




}