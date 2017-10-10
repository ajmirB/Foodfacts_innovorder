package com.busgeeth.foodfacts.core.stores;

import android.util.Log;

import com.busgeeth.foodfacts.App;
import com.busgeeth.foodfacts.SharedApplication;
import com.busgeeth.foodfacts.core.model.db.DbProduct;
import com.busgeeth.foodfacts.core.model.db.DbProductDao;
import com.busgeeth.foodfacts.core.model.entities.Product;
import com.busgeeth.foodfacts.helpers.ProductHelper;

import io.reactivex.Completable;
import io.reactivex.Maybe;

public class ProductStore {

    private static final String TAG = ProductStore.class.getSimpleName();

    DbProductDao mProductDao;

    public ProductStore() {
        App app = SharedApplication.getInstance().getApplication();
        mProductDao = app.getDaoSession().getDbProductDao();
    }

    /**
     * Get a product from the db by its barcode
     * @param barcodeNumber barcode to identify the product
     * @return the product store in the db, or an empty response if not
     */
    public Maybe<Product> getProduct(long barcodeNumber) {
        return Maybe.defer(() -> {
            DbProduct dbProduct = mProductDao.loadByRowId(barcodeNumber);
            if (dbProduct == null) {
                return Maybe.empty();
            } else {
                Log.d(TAG, "getProduct: product found");
                return Maybe.just(ProductHelper.dbToProduct(dbProduct));
            }
        });
    }

    /**
     * Save a product to the db
     * @param product product to save in the db
     * @return a completable which complete immediatly if the save has succeed
     */
    public Completable saveProduct(Product product) {
        return Completable.defer(() -> {
            DbProduct dbProduct = ProductHelper.entitytoDbProduct(product);
            mProductDao.insertOrReplaceInTx(dbProduct);
            return Completable.complete();
        });
    }
}
