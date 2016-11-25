package com.main.mygym.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.main.mygym.Const;
import com.main.mygym.R;
import com.main.mygym.adapter.CategoryRecyclerAdapter;
import com.main.mygym.api.ApiManager;
import com.main.mygym.api.response.CategoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesActivity extends AppCompatActivity {

    private String mTitle;

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

        ApiManager.getInstance().getCategories().enqueue(new Callback<List<CategoryResponse>>() {
            @Override
            public void onResponse(Call<List<CategoryResponse>> call, Response<List<CategoryResponse>> response) {

                mAdapter = new CategoryRecyclerAdapter(response.body().get(0).getCategoryList(), mTitle);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<CategoryResponse>> call, Throwable t) {
                Toast.makeText(CategoriesActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
