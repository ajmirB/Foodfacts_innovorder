package com.busgeeth.foodfacts.features.core.model.entities;

import com.google.gson.annotations.SerializedName;

public class ProductResponse {

    @SerializedName("status")
    private ProductStatus mStatus;

    @SerializedName("status_verbose")
    private String mStatusVerbose;

    @SerializedName("code")
    private long mCode;

    @SerializedName("product")
    private Product mProduct;

    public ProductStatus getStatus() {
        return mStatus != null ? mStatus : ProductStatus.UNKNOWN;
    }

    public String getStatusVerbose() {
        return mStatusVerbose;
    }

    public long getCode() {
        return mCode;
    }

    public Product getProduct() {
        return mProduct;
    }
}
