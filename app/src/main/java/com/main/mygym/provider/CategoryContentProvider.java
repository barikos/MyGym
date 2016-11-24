package com.main.mygym.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.main.mygym.util.DBHelper;

/**
 * Created by barikos on 21.11.16.
 */
public class CategoryContentProvider extends ContentProvider{

    private static final String AUTHORITY = "com.main.mygym.provider.Exercises";
    private static final String EXERCISES_PATH = "exercises";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + EXERCISES_PATH);

   // private static final String EXERCISE_CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + EXERCISES_PATH;
   // private static final String EXERCISE_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + EXERCISES_PATH;

    private static final int URI_EXERCISES = 1;
    private static final int URI_EXERCISE_ID = 2;

    private static final UriMatcher uriMather;
    static {
        uriMather = new UriMatcher(UriMatcher.NO_MATCH);
        uriMather.addURI(AUTHORITY,EXERCISES_PATH,URI_EXERCISES);
        uriMather.addURI(AUTHORITY,EXERCISES_PATH +"/#",URI_EXERCISE_ID);
    }

    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    @Override
    public boolean onCreate() {
        mDBHelper = new DBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        mDB = mDBHelper.getWritableDatabase();
        switch (uriMather.match(uri)){
            case URI_EXERCISES:
                //do nothing
                break;
            case URI_EXERCISE_ID:
                String id = uri.getPathSegments().get(1);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        Cursor cursor = mDB.query(DBHelper.TABLE_EXERCISES,projection,selection,selectionArgs,null,null,sortOrder);
        //cursor.setNotificationUri(getContext().getContentResolver(),CONTENT_URI);
        return cursor;
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        mDB = mDBHelper.getWritableDatabase();
        switch (uriMather.match(uri)){
            case URI_EXERCISES:
                //do nothing
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        long rowID = mDB.insert(DBHelper.TABLE_EXERCISES,null,values);
        Uri resultUri = ContentUris.withAppendedId(CONTENT_URI, rowID);
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        mDB = mDBHelper.getWritableDatabase();
        switch (uriMather.match(uri)) {
            case URI_EXERCISES:
                //do nothing
                break;
            case URI_EXERCISE_ID:
                String id = uri.getPathSegments().get(1);
                selection = DBHelper.KEY_EX_ID + "=" + id +
                        (!TextUtils.isEmpty(selection) ? "AND (" + selection + ')' : "");
        }
        int cnt = mDB.delete(DBHelper.TABLE_EXERCISES, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri,null);
        return cnt;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        mDB = mDBHelper.getWritableDatabase();
        switch (uriMather.match(uri)){
            case URI_EXERCISES:
                //do nothing
                break;
            case URI_EXERCISE_ID:
                String id = uri.getPathSegments().get(1);
                selection = DBHelper.KEY_EX_ID + "=" + id + (!TextUtils.isEmpty(selection) ? "AND (" + selection + ')' : "");
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        int updateCount = mDB.update(DBHelper.TABLE_EXERCISES,values,selection,selectionArgs);
        getContext().getContentResolver().notifyChange(uri,null);
        return updateCount;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMather.match(uri)){
            case URI_EXERCISES:
                return "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + EXERCISES_PATH;
            case URI_EXERCISE_ID:
                return "vnd.android.cursor.item/vnd." + AUTHORITY + "." + EXERCISES_PATH;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}
