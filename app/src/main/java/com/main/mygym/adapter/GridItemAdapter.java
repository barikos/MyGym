package com.main.mygym.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.main.mygym.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by barikos on 19.11.16.
 */
public class GridItemAdapter extends BaseAdapter {

    private final List<Item> mItems = new ArrayList<Item>();
    private LayoutInflater mLayoutInflater;

    public GridItemAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItems.add(new Item(context.getString(R.string.monday), R.drawable.monday));
        mItems.add(new Item(context.getString(R.string.tuesday), R.drawable.tuesday));
        mItems.add(new Item(context.getString(R.string.wednesday), R.drawable.wednesday));
        mItems.add(new Item(context.getString(R.string.thursday), R.drawable.thursday));
        mItems.add(new Item(context.getString(R.string.friday), R.drawable.friday));
        mItems.add(new Item(context.getString(R.string.saturday), R.drawable.saturday));
        mItems.add(new Item(context.getString(R.string.sunday), R.drawable.sunday));
        mItems.add(new Item("", R.drawable.bg_timer));
    }

    @Override
    public int getCount() {
        return mItems.size();
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
        if (convertView == null) {
            listViewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.grid_item, parent, false);
            listViewHolder.textInListView = (TextView) convertView.findViewById(R.id.txt_gird_item);
            listViewHolder.imageInListView = (ImageView) convertView.findViewById(R.id.img_grid_item);
            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (ViewHolder) convertView.getTag();
        }

        listViewHolder.textInListView.setText(mItems.get(position).getName());
        listViewHolder.imageInListView.setImageResource(mItems.get(position).getDrawableId());

        return convertView;
    }

    static class ViewHolder {
        TextView textInListView;
        ImageView imageInListView;
    }

    private static class Item {
        private final String name;
        private final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }

        public String getName() {
            return name;
        }

        public int getDrawableId() {
            return drawableId;
        }
    }
}
