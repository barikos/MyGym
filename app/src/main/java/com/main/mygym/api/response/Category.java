package com.main.mygym.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by barikos on 20.11.16.
 */
public class Category {
    @SerializedName("id")
    private Integer mId;
    @SerializedName("name")
    private String mName;


    public Integer getId() {
        return mId;
    }

    public Category setId(Integer id) {
        mId = id;
        return this;
    }

    public String getName() {
        return mName;
    }

    public Category setName(String name) {
        mName = name;
        return this;
    }
}
