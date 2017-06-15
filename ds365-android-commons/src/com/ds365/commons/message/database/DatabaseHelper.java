package com.ds365.commons.message.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author 杨晓振
 *         on 2016/12/21.
 *         存消息内容用的SQLlite
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "messageDatabase.db";
    private String tableName;
    
    public DatabaseHelper(Context context,String tableName){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        this.tableName = tableName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,messageid INTEGER,messageCategory INTEGER,messageType INTEGER," +
                "title TEXT,sendTime TEXT,content TEXT,messageFunctionType INTEGER,readFlag INTEGER,expiryTime TEXT,paramsMap TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE message ADD COLUMN other STRING");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
