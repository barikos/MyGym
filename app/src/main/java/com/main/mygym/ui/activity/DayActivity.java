package com.main.mygym.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.main.mygym.Const;
import com.main.mygym.R;
import com.main.mygym.adapter.ExercisesCursorAdapter;
import com.main.mygym.provider.Contract;

public class DayActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private Button mButton;
    private String mTitle;
    private Toolbar mToolbar;

    private LinearLayout mHolder;
    private ListView mListView;
    private ExercisesCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        mTitle = getIntent().getStringExtra(Const.KEY_EXTRA_DAY);
        mToolbar = (Toolbar) findViewById(R.id.day_tool_bar);
        setSupportActionBar(mToolbar);

        mButton = (Button) findViewById(R.id.btn_fragment_placeholder);
        mHolder = (LinearLayout) findViewById(R.id.day_placeholder);
        mListView = (ListView) findViewById(R.id.day_list_view);
        mAdapter = new ExercisesCursorAdapter(this, null, 0);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().initLoader(Contract.LOADER_ID, null, this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DayActivity.this, CategoriesActivity.class)
                        .putExtra(Const.KEY_EXTRA_DAY, mTitle));
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] selectionArgs = {mTitle};
        return new CursorLoader(this, Contract.CONTENT_URI, null, Contract.SELECTION, selectionArgs, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
        if (data.moveToFirst()) {
            mHolder.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
        } else {
            mHolder.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

}

