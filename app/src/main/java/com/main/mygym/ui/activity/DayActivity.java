package com.main.mygym.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.main.mygym.Const;
import com.main.mygym.R;
import com.main.mygym.adapter.ExercisesCursorAdapter;
import com.main.mygym.provider.Contract;
import com.main.mygym.ui.fragment.DayDialogFragment;

public class DayActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private Button mButton;
    private String mTitle;
    private Toolbar mToolbar;
    private TextView mToolbarTitle;
    private ImageView mToolbarAdd;
    private ImageView mToolbarBack;
    private LinearLayout mHolder;
    private ListView mListView;

    private ExercisesCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        mTitle = getIntent().getStringExtra(Const.KEY_EXTRA_DAY);
        mToolbar = (Toolbar) findViewById(R.id.day_tool_bar);
        mToolbarAdd = (ImageView) mToolbar.findViewById(R.id.img_toolbar_add);
        mToolbarBack = (ImageView) mToolbar.findViewById(R.id.img_toolbar_back);
        mToolbarTitle = (TextView) mToolbar.findViewById(R.id.txt_toolbar_title);
        mButton = (Button) findViewById(R.id.btn_fragment_placeholder);
        mHolder = (LinearLayout) findViewById(R.id.day_placeholder);
        mListView = (ListView) findViewById(R.id.day_list_view);
        mAdapter = new ExercisesCursorAdapter(this, null, 0);
        mListView.setAdapter(mAdapter);

        mToolbarTitle.setText(mTitle);
        mToolbarAdd.setImageResource(R.drawable.ic_add);
        mToolbarBack.setImageResource(R.drawable.ic_back);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().initLoader(Contract.LOADER_ID, null, this);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategoriesActivity();
            }
        });
        mToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbarAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCategoriesActivity();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExercisesCursorAdapter.ViewHolder holder = (ExercisesCursorAdapter.ViewHolder) view.getTag();
                showDialog(holder.id, holder.textView.getText().toString());
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
            mHolder.setVisibility(View.INVISIBLE);
            mListView.setVisibility(View.VISIBLE);
        } else {
            mHolder.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    private void startCategoriesActivity() {
        Intent intent = new Intent(DayActivity.this, CategoriesActivity.class);
        intent.putExtra(Const.KEY_EXTRA_DAY, mTitle);
        startActivity(intent);
    }

    private void showDialog(long id, String name) {
        DialogFragment dialog = DayDialogFragment.getInstance(id, name);
        dialog.show(getSupportFragmentManager(), "dialog_day");
    }

}

