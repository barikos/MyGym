package com.main.mygym.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by barikos on 20.11.16.
 */
public class DBHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gymDB";

    public static final String TABLE_EXERCISES = "exercises";
    public static final String KEY_EX_ID = "_id";
    public static final String KEY_EX_NAME = "name";
    public static final String KEY_EX_DAY = "day";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        db.execSQL(String.format("create table %s(%s integer primary key autoincrement, %s text, " +
                " %s text)", TABLE_EXERCISES, KEY_EX_ID, KEY_EX_NAME, KEY_EX_DAY));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
