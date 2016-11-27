package com.main.mygym.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.main.mygym.Const;
import com.main.mygym.R;
import com.main.mygym.adapter.GridItemAdapter;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mToolbarTitle;
    private ImageView mToolbarImage;
    private GridView mGridView;
    private GridItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (GridView) findViewById(R.id.main_grid_view);
        mToolbar = (Toolbar) findViewById(R.id.main_tool_bar);
        mToolbarTitle = (TextView) mToolbar.findViewById(R.id.txt_toolbar_title);
        mToolbarImage = (ImageView) mToolbar.findViewById(R.id.img_toolbar_add);

        setSupportActionBar(mToolbar);
        mToolbarTitle.setText(R.string.app_name);
        mToolbarImage.setImageResource(R.drawable.ic_about);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mAdapter = new GridItemAdapter(this);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 7:
                        Toast.makeText(MainActivity.this, R.string.creating, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        TextView textView = (TextView) view.findViewById(R.id.txt_gird_item);
                        String text = textView.getText().toString();
                        startActivity(new Intent(MainActivity.this, DayActivity.class).putExtra(Const.KEY_EXTRA_DAY, text));
                        break;
                }
            }
        });
    }

}
