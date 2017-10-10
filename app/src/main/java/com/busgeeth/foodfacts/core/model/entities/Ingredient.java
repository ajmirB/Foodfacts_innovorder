package com.busgeeth.foodfacts.core.model.entities;

import com.google.gson.annotations.SerializedName;

public class Ingredient {

    @SerializedName("id")
    private String mId;

    @SerializedName("text")
    private String mText;

    @SerializedName("rank")
    private int mRank;

    public String getId() {
        return mId;
    }

    public String getText() {
        return mText;
    }

    public int getRank() {
        return mRank;
    }
}
