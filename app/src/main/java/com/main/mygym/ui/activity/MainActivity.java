package com.main.mygym.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.main.mygym.Const;
import com.main.mygym.R;
import com.main.mygym.adapter.GridItemAdapter;
import com.main.mygym.ui.DayModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private GridView mGridView;
    private GridItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (GridView) findViewById(R.id.main_grid_view);
        mToolbar = (Toolbar)findViewById(R.id.main_tool_bar);
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mAdapter = new GridItemAdapter(this,getDayList());
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 7:
                        Toast.makeText(MainActivity.this, R.string.creating, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        TextView textView = (TextView)view.findViewById(R.id.gird_item_txt);
                        String text = textView.getText().toString();
                        startActivity(new Intent(MainActivity.this, DayActivity.class).putExtra(Const.KEY_EXTRA_DAY, text));
                        break;
                }
            }
        });

    }

    private List<DayModel> getDayList(){
        List<DayModel> arrayList = new ArrayList<DayModel>();
        arrayList.add(new DayModel().setDay(R.string.monday).setImageRecourse(R.drawable.monday));
        arrayList.add(new DayModel().setDay(R.string.tuesday).setImageRecourse(R.drawable.tuesday));
        arrayList.add(new DayModel().setDay(R.string.wednesday).setImageRecourse(R.drawable.wednesday));
        arrayList.add(new DayModel().setDay(R.string.thursday).setImageRecourse(R.drawable.thursday));
        arrayList.add(new DayModel().setDay(R.string.friday).setImageRecourse(R.drawable.friday));
        arrayList.add(new DayModel().setDay(R.string.saturday).setImageRecourse(R.drawable.saturday));
        arrayList.add(new DayModel().setDay(R.string.sunday).setImageRecourse(R.drawable.sunday));
        arrayList.add(new DayModel().setDay(R.string.nothing).setImageRecourse(R.drawable.bg_timer));
        return arrayList;
    }
}
