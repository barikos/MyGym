package com.main.mygym.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Window;
import android.widget.TextView;

import com.main.mygym.Const;

/**
 * Created by barikos on 24.11.16.
 */
public class CategoryDialogFragment extends DialogFragment {

    private TextView mTextView;

    public static DayDialogFragment getInstance(String name) {
        DayDialogFragment dialog = new DayDialogFragment();
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


}
