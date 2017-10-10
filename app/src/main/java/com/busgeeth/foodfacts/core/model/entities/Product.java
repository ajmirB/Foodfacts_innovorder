package com.busgeeth.foodfacts.core.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {

    @SerializedName("code")
    private long mBarcode;

    @SerializedName("product_name")
    private String mName;

    @SerializedName("image_thumb_url")
    private String mImageUrl;

    @SerializedName("ingredients")
    private List<Ingredient> mIngredientList;


    public long getBarcode() {
        return mBarcode;
    }

    public String getName() {
        return mName;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public List<Ingredient> getIngredientList() {
        return mIngredientList;
    }
}
