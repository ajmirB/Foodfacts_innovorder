package com.busgeeth.foodfacts.core.model.entities;

import com.google.gson.annotations.SerializedName;

public enum ProductStatus {
    @SerializedName("0")
    NOT_FOUND,
    @SerializedName("1")
    FOUND,
    UNKNOWN;
}
