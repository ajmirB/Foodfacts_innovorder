package com.busgeeth.foodfacts.core.model.entities;

import com.google.gson.annotations.SerializedName;

public class Nutriments {

    @SerializedName("energy")
    private String mEnergy;

    public String getEnergy() {
        return mEnergy;
    }
}
