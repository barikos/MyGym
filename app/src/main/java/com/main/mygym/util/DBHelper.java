package com.main.mygym.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.main.mygym.R;

/**
 * Created by barikos on 20.11.16.
 */
public class DBHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gymDB";

    public static final String TABLE_DAYS = "days";
    public static final String KEY_DAY_ID = "_id";
    public static final String KEY_DAY_NAME = "name";

    public static final String TABLE_EXERCISES = "exercises";
    public static final String KEY_EX_ID = "_id";
    public static final String KEY_EX_NAME = "name";
    public static final String KEY_EX_DAY_ID = "day_id";

    String[] mas = {String.valueOf(R.string.monday),
            String.valueOf(R.string.tuesday),
            String.valueOf(R.string.wednesday),
            String.valueOf(R.string.thursday),
            String.valueOf(R.string.friday),
            String.valueOf(R.string.saturday),
            String.valueOf(R.string.sunday)};
    String[] mas2 = {"GYM", "GYM2"};

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        db.execSQL(String.format("create table %s(%s integer primary key, %s text)",
                TABLE_DAYS,KEY_DAY_ID,KEY_DAY_NAME));
        for (int i=0; i<mas.length; i++){
            cv.clear();
            cv.put(KEY_DAY_ID, i);
            cv.put(KEY_DAY_NAME, mas[i]);
            db.insert(TABLE_DAYS,null,cv);
        }

        db.execSQL(String.format("create table %s(%s integer primary key autoincrement, %s text, %s integer, " +
                "foreign key (%s) references %s(%s))",
                TABLE_EXERCISES,KEY_EX_ID,KEY_EX_NAME,KEY_EX_DAY_ID,KEY_EX_DAY_ID,TABLE_DAYS,KEY_DAY_ID));
        for (int i=0; i<2; i++){
            cv.clear();
            cv.put(KEY_EX_ID, i);
            cv.put(KEY_EX_NAME,mas2[i]);
            cv.put(KEY_EX_DAY_ID,1);
            db.insert(TABLE_EXERCISES,null,cv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
