package com.main.mygym.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by barikos on 20.11.16.
 */
public class CategoryResponse {
    @SerializedName("id")
    private Integer mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("parent_id")
    private Object mParentId;
    @SerializedName("application_id")
    private Integer mApplicationId;
    @SerializedName("articles")
    private List<Object> mArticles = new ArrayList<Object>();
    @SerializedName("categories")
    private List<Category> mCategoryList = new ArrayList<Category>();

    public Integer getId() {
        return mId;
    }

    public CategoryResponse setId(Integer id) {
        this.mId = id;
        return this;
    }

    public String getName() {
        return mName;
    }

    public CategoryResponse setName(String name) {
        this.mName = name;
        return this;
    }

    public Object getParentId() {
        return mParentId;
    }

    public CategoryResponse setParentId(Object parentId) {
        this.mParentId = parentId;
        return this;
    }

    public Integer getApplicationId() {
        return mApplicationId;
    }

    public CategoryResponse setApplicationId(Integer applicationId) {
        this.mApplicationId = applicationId;
        return this;
    }

    public List<Object> getArticles() {
        return mArticles;
    }

    public CategoryResponse setArticles(List<Object> articles) {
        this.mArticles = articles;
        return this;
    }

    public List<Category> getCategoryList() {
        return mCategoryList;
    }

    public CategoryResponse setCategoryList(List<Category> categoryList) {
        this.mCategoryList = categoryList;
        return this;
    }
}
