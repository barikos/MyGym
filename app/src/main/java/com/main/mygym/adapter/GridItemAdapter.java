package com.main.mygym.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.main.mygym.R;
import com.main.mygym.ui.DayModel;

import java.util.List;

/**
 * Created by barikos on 19.11.16.
 */
public class GridItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<DayModel> mDataList;
    private LayoutInflater mLayoutInflater;


    public GridItemAdapter(Context context, List<DayModel> dataList) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.grid_item, parent, false);
            listViewHolder.textInListView = (TextView) convertView.findViewById(R.id.txt_gird_item);
            listViewHolder.imageInListView = (ImageView)convertView.findViewById(R.id.grid_item_img);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }

        listViewHolder.textInListView.setText(mDataList.get(position).getDay());
        listViewHolder.imageInListView.setImageResource(mDataList.get(position).getImageRecourse());

        return convertView;
    }

    static class ViewHolder{
        TextView textInListView;
        ImageView imageInListView;
    }
}
