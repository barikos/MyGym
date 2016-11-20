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
    private List<Category> mCategories = new ArrayList<Category>();

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public Object getParentId() {
        return mParentId;
    }

    public void setParentId(Object parentId) {
        this.mParentId = parentId;
    }

    public Integer getApplicationId() {
        return mApplicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.mApplicationId = applicationId;
    }

    public List<Object> getArticles() {
        return mArticles;
    }

    public void setArticles(List<Object> articles) {
        this.mArticles = articles;
    }

    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Category> categories) {
        this.mCategories = categories;
    }
}
