package com.main.mygym.adapter;

import android.content.ContentValues;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.main.mygym.R;
import com.main.mygym.api.response.Category;
import com.main.mygym.provider.Contract;
import com.main.mygym.util.DBHelper;

import java.util.List;

/**
 * Created by barikos on 21.11.16.
 */
public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder> {

    private List<Category> mCategoryList;
    private String mDay;
    private OnTextClickListener mOnTextClickListener;

    public CategoryRecyclerAdapter(List<Category> categoryList, String day) {
        mCategoryList = categoryList;
        mDay = day;
    }

    public interface OnTextClickListener {
        void onTextClick(String name);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt_recycler_view_item);
            imageView = (ImageView) itemView.findViewById(R.id.img_recycle_view_item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Category category = mCategoryList.get(position);
        final String text = category.getName();
        holder.textView.setText(text);
        holder.imageView.setImageResource(R.drawable.ic_add);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put(DBHelper.KEY_EX_NAME, text);
                cv.put(DBHelper.KEY_EX_DAY, mDay);
                v.getContext().getContentResolver().insert(Contract.CONTENT_URI, cv);
                mCategoryList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mCategoryList.size());
            }
        });
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnTextClickListener.onTextClick(text);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    public void setCategoryList(List<Category> categoryList) {
        mCategoryList = categoryList;
    }

    public void setOnTextClickListener(OnTextClickListener listener) {
        mOnTextClickListener = listener;
    }
}
