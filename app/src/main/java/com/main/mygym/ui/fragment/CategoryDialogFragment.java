package com.main.mygym.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.main.mygym.Const;
import com.main.mygym.R;

/**
 * Created by barikos on 24.11.16.
 */
public class CategoryDialogFragment extends DialogFragment {

    private TextView mTextView;

    public static CategoryDialogFragment getInstance(String name) {
        CategoryDialogFragment dialog = new CategoryDialogFragment();
        Bundle args = new Bundle();
        args.putString(Const.CAT_ARG_NAME, name);
        dialog.setArguments(args);
        dialog.setStyle(STYLE_NO_FRAME, 0);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_categories, container, false);
        mTextView = (TextView) view.findViewById(R.id.txt_dialog_categories);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTextView.setText(getArguments().getString(Const.CAT_ARG_NAME));
    }
}
