package com.busgeeth.foodfacts.helpers;

import com.busgeeth.foodfacts.SharedApplication;
import com.busgeeth.foodfacts.core.model.db.DbProduct;
import com.busgeeth.foodfacts.core.model.entities.Product;
import com.google.gson.Gson;

public class ProductHelper {

    /**
     * Convert a dbProduct to a product
     * @param dbProduct the dbProduct to convert
     * @return the product converted from the dbProduct
     */
    public static Product dbToProduct(DbProduct dbProduct) {
        Gson gson = SharedApplication.getInstance().getGson();
        return gson.fromJson(dbProduct.getMRawData(), Product.class);
    }

    /**
     * Convert a product to a dbProduct
     * @param product the product to convert
     * @return the dbProduct converted from the product
     */
    public static DbProduct entitytoDbProduct(Product product) {
        Gson gson = SharedApplication.getInstance().getGson();
        String rawData = gson.toJson(product);
        long barcode = product.getBarcode();

        return new DbProduct(barcode, rawData);
    }
}
