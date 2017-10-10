package com.busgeeth.foodfacts.core.model.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DbProduct {

    @Id
    Long barcode;

    @NotNull
    String mRawData;

    @Generated(hash = 673094190)
    public DbProduct(Long barcode, @NotNull String mRawData) {
        this.barcode = barcode;
        this.mRawData = mRawData;
    }

    @Generated(hash = 1388400241)
    public DbProduct() {
    }

    public Long getBarcode() {
        return this.barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public String getMRawData() {
        return this.mRawData;
    }

    public void setMRawData(String mRawData) {
        this.mRawData = mRawData;
    }
    
}
