package com.main.mygym.ui;

/**
 * Created by barikos on 20.11.16.
 */
public class DayModel {
    private int mDay;
    private int mImageRecourse;

    public int getDay() {
        return mDay;
    }

    public DayModel setDay(int day) {
        mDay = day;
        return this;
    }

    public int getImageRecourse() {
        return mImageRecourse;
    }

    public DayModel setImageRecourse(int imageRecourse) {
        mImageRecourse = imageRecourse;
        return this;
    }
}
