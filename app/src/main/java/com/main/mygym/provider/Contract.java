package com.main.mygym.provider;

import android.net.Uri;

import com.main.mygym.util.DBHelper;

/**
 * Created by barikos on 24.11.16.
 */
public class Contract {

    public static final String AUTHORITY = "com.main.mygym.provider.Exercises";
    public static final String EXERCISES_PATH = "exercises";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + EXERCISES_PATH);


    public static final int URI_EXERCISES = 1;
    public static final int URI_EXERCISE_ID = 2;

    public static final String SELECTION = String.format("%s = ?", DBHelper.KEY_EX_DAY);

    public static final String[] DEFAULT_PROJECTION = {DBHelper.KEY_EX_ID, DBHelper.KEY_EX_NAME};

    public static final int LOADER_ID = 0;

}
