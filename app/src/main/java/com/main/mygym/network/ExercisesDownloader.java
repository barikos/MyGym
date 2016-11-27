package com.main.mygym.network;

import android.os.AsyncTask;

import com.main.mygym.api.ApiManager;
import com.main.mygym.api.response.Category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by barikos on 26.11.16.
 */
public class ExercisesDownloader extends AsyncTask<Void, Void, List<Category>> {

    public interface DataLoadedListener {
        void getListExercises(List<Category> categoryList);
    }

    private List<Category> mCategoryList = new ArrayList<>();
    private DataLoadedListener mListener;

    public ExercisesDownloader(DataLoadedListener listener) {
        mListener = listener;
    }

    @Override
    protected List<Category> doInBackground(Void... params) {

        try {
            mCategoryList = ApiManager.getInstance()
                    .getCategories().execute().body().get(0).getCategoryList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Category> categories) {
        super.onPostExecute(categories);
        mListener.getListExercises(mCategoryList);
        return;
    }

}
