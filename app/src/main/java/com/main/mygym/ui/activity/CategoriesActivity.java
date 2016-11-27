package com.main.mygym.ui.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.main.mygym.Const;
import com.main.mygym.R;
import com.main.mygym.adapter.CategoryRecyclerAdapter;
import com.main.mygym.api.response.Category;
import com.main.mygym.network.ExercisesDownloader;
import com.main.mygym.provider.Contract;
import com.main.mygym.ui.fragment.CategoryDialogFragment;
import com.main.mygym.util.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity
        implements CategoryRecyclerAdapter.OnTextClickListener,
        ExercisesDownloader.DataLoadedListener {

    private String mTitle;
    private List<Category> mCategoryList = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
    private CategoryRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        mTitle = getIntent().getStringExtra(Const.KEY_EXTRA_DAY);
        mRecyclerView = (RecyclerView)findViewById(R.id.cat_recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();

        new ExercisesDownloader(this).execute();

        mAdapter = new CategoryRecyclerAdapter(mCategoryList, mTitle);
        mAdapter.setOnTextClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onTextClick(String name) {
        DialogFragment dialog = CategoryDialogFragment.getInstance(name);
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void getListExercises(List<Category> categoryList) {
        mAdapter.setCategoryList(getIntersection(categoryList));
        mAdapter.notifyDataSetChanged();
    }

    private List<Category> getIntersection(List<Category> categoryList) {
        List<Category> list = new ArrayList<>(categoryList);
        String[] selectionArgs = {mTitle};
        Cursor cursor = getContentResolver().query(Contract.CONTENT_URI, null, Contract.SELECTION, selectionArgs, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_EX_NAME));
            for (int i = 0; i < list.size(); i++) {
                if (name.equals(list.get(i).getName())) {
                    list.remove(list.get(i));
                }
            }
        }
        return list;
    }
}
