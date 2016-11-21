package com.main.mygym.ui.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.main.mygym.R;
import com.main.mygym.util.DBHelper;

public class DayActivity extends AppCompatActivity {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        mButton = (Button)findViewById(R.id.btn_day_add);
        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
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
    }
}

