package com.main.mygym.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.main.mygym.R;
import com.main.mygym.adapter.ExercisesCursorAdapter;
import com.main.mygym.provider.CategoryContentProvider;
import com.main.mygym.ui.fragment.DayDialogFragment;

public class DayActivity extends AppCompatActivity {

    private Button mButton;
    private ListView mListView;
    private ExercisesCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        mButton = (Button)findViewById(R.id.btn_day_add);
        mListView = (ListView) findViewById(R.id.day_list_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DayActivity.this, CategoriesActivity.class));
            }
        });

        Cursor cursor = getContentResolver().query(CategoryContentProvider.CONTENT_URI,null,null,null,null);
        mAdapter = new ExercisesCursorAdapter(this,cursor,0);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExercisesCursorAdapter.ViewHolder holder = (ExercisesCursorAdapter.ViewHolder) view.getTag();
                if (holder != null) {
                    showDialog(holder.id, holder.textView.getText().toString());
                } else return;
            }
        });
    }

    private void showDialog(long id, String name) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FragmentManager fm = getSupportFragmentManager();
        DayDialogFragment dialog = DayDialogFragment.getInstance(id, name);
        dialog.show(fm, "dialog");
    }
}

