package com.xarv.flatchat;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME="storage.db";


    //MESSAGE TABLE
    private static final String ID = "_id";
    private static final String MESSAGE_TABLE = "MSG_TABLE";
    private static final String MESSAGE_ID = "MSG_ID";
    private static final String MESSAGE_TYPE ="MSG_TYPE";
    private static final String MESSAGE_DATA = "MSG_DATA";
    private static final String MESSAGE_TIMESTAMP = "MSG_TIMESTAMP";

    public final String[] MESSAGE_COL={"_id","MSG_ID","MSG_TYPE","MSG_DATA","MSG_TIMESTAMP"};

    private static final String MESSAGE_TABLE_CREATE=
            "CREATE TABLE " + MESSAGE_TABLE +" ( " +
                    ID + " TEXT NOT NULL,"+
                    MESSAGE_ID + " TEXT PRIMARY KEY NOT NULL, " +
                    MESSAGE_TYPE + " TEXT NOT NULL, " +
                    MESSAGE_DATA + " TEXT NOT NULL, " +
                    MESSAGE_TIMESTAMP + " TEXT NOT NULL " +
                    ");";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MESSAGE_TABLE_CREATE);


    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // upgrade
    }
    public boolean  insertMsgTable(String id,String msgId, String msgData,String msgType,String msgTime)
    {
        try {

            SQLiteDatabase database=  this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(ID,id);
            values.put(MESSAGE_ID, msgId);
            values.put(MESSAGE_DATA, msgData);
            values.put(MESSAGE_TYPE, msgType);
            values.put(MESSAGE_TIMESTAMP, msgTime);

            Long rowID = database.insertWithOnConflict(MESSAGE_TABLE,null,values,SQLiteDatabase.CONFLICT_IGNORE);

            if(rowID < 0)
                return false;
            else
                return true;
        } catch (Exception e) {
            return false;

        }

    }

    public Cursor getMessages(){
        Cursor cursor = getReadableDatabase().rawQuery("select * from MSG_TABLE",null);
        return cursor;
    }




}