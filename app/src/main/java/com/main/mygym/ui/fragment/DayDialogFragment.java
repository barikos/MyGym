package com.main.mygym.ui.fragment;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.main.mygym.Const;
import com.main.mygym.R;
import com.main.mygym.provider.Contract;

/**
 * Created by barikos on 22.11.16.
 */
public class DayDialogFragment extends DialogFragment implements View.OnClickListener {

    private long mId;
    private String mName;
    private TextView mTextView;
    private ImageView mDelete, mCancel;

    public static DayDialogFragment getInstance(long id, String name) {
        DayDialogFragment dialog = new DayDialogFragment();
        Bundle args = new Bundle();
        args.putLong(Const.DAY_ARG_ID, id);
        args.putString(Const.DAY_ARG_NAME, name);
        dialog.setArguments(args);
        dialog.setStyle(STYLE_NO_FRAME, 0);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = getArguments().getLong(Const.DAY_ARG_ID);
        mName = getArguments().getString(Const.DAY_ARG_NAME);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_day, container, false);
        mTextView = (TextView) view.findViewById(R.id.txt_dialog_day);
        mDelete = (ImageView) view.findViewById(R.id.img_dialog_day_delete);
        mCancel = (ImageView) view.findViewById(R.id.img_dialog_day_cancel);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        StringBuilder text = new StringBuilder();
        text
                .append(getString(R.string.txt_dialog_day_1))
                .append(mName)
                .append(getString(R.string.txt_dialog_day_2))
                .append(" ")
                .append(mId);
        mTextView.setText(text);
        mDelete.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_dialog_day_cancel:
                dismiss();
                break;
            case R.id.img_dialog_day_delete:
                Uri uri = Uri.parse(Contract.CONTENT_URI + "/" + mId);
                getContext().getContentResolver().delete(uri, null, null);
                dismiss();
                break;
        }
    }
}
