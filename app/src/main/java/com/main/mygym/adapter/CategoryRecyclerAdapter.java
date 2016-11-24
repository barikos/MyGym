package com.main.mygym.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        ImageView imageView;

        public CategoryViewHolder(final View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt_recycler_view_item);
            imageView = (ImageView) itemView.findViewById(R.id.img_recycle_view_item);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "text", Toast.LENGTH_SHORT).show();
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "image", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Category category = mCategoryList.get(position);
        holder.textView.setText(category.getName());
        holder.imageView.setImageResource(R.drawable.ic_add);
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }


}
