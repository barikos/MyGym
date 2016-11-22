package com.main.mygym.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.main.mygym.R;
import com.main.mygym.api.response.Category;

import java.util.List;

/**
 * Created by barikos on 21.11.16.
 */
public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder>{

    private List<Category> mCategoryList;

    public CategoryRecyclerAdapter(List<Category> categoryList) {
        mCategoryList = categoryList;
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.list_view_txt);
        }
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Category category = mCategoryList.get(position);
        holder.textView.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }


}
