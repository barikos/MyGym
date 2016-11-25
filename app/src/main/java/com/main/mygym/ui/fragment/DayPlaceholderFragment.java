package com.main.mygym.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.main.mygym.R;
import com.main.mygym.ui.activity.CategoriesActivity;

/**
 * Created by barikos on 24.11.16.
 */
public class DayPlaceholderFragment extends Fragment {

    Button mButton;

    public static DayPlaceholderFragment getInstance() {
        return new DayPlaceholderFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.placeholder_day, null);
        mButton = (Button) view.findViewById(R.id.btn_fragment_placeholder);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CategoriesActivity.class));
            }
        });
    }
}
