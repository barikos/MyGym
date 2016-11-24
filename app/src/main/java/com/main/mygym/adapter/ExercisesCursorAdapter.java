package com.main.mygym.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.main.mygym.R;
import com.main.mygym.util.DBHelper;

/**
 * Created by barikos on 22.11.16.
 */
public class ExercisesCursorAdapter extends CursorAdapter{

    private LayoutInflater mInflater;

    public static class ViewHolder {
        public TextView textView;
        public long id;
    }

    public ExercisesCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.list_view_item, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.textView = (TextView) view.findViewById(R.id.txt_list_view_item);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(DBHelper.KEY_EX_ID));
        String name = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_EX_NAME));
        ViewHolder holder  =   (ViewHolder)    view.getTag();
        if (holder != null){
            holder.textView.setText(name);
            holder.id = id;
        }
    }
}
