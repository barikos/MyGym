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

    private static final UriMatcher uriMather;
    static {
        uriMather = new UriMatcher(UriMatcher.NO_MATCH);
        uriMather.addURI(Contract.AUTHORITY, Contract.EXERCISES_PATH, Contract.URI_EXERCISES);
        uriMather.addURI(Contract.AUTHORITY, Contract.EXERCISES_PATH + "/#", Contract.URI_EXERCISE_ID);
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
            case Contract.URI_EXERCISES:
                //do nothing
                break;
            case Contract.URI_EXERCISE_ID:
                String id = uri.getPathSegments().get(1);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        Cursor cursor = mDB.query(DBHelper.TABLE_EXERCISES,projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), Contract.CONTENT_URI);
        return cursor;
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        mDB = mDBHelper.getWritableDatabase();
        switch (uriMather.match(uri)){
            case Contract.URI_EXERCISES:
                //do nothing
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        long rowID = mDB.insert(DBHelper.TABLE_EXERCISES, null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(Contract.CONTENT_URI, rowID);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        mDB = mDBHelper.getWritableDatabase();
        switch (uriMather.match(uri)) {
            case Contract.URI_EXERCISES:
                //do nothing
                break;
            case Contract.URI_EXERCISE_ID:
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
            case Contract.URI_EXERCISES:
                //do nothing
                break;
            case Contract.URI_EXERCISE_ID:
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
            case Contract.URI_EXERCISES:
                return "vnd.android.cursor.dir/vnd." + Contract.AUTHORITY + "." + Contract.EXERCISES_PATH;
            case Contract.URI_EXERCISE_ID:
                return "vnd.android.cursor.item/vnd." + Contract.AUTHORITY + "." + Contract.EXERCISES_PATH;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}
