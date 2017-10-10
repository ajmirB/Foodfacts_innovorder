package com.busgeeth.foodfacts.core.stores;

import android.util.Log;

import com.busgeeth.foodfacts.App;
import com.busgeeth.foodfacts.SharedApplication;
import com.busgeeth.foodfacts.core.model.db.DbProduct;
import com.busgeeth.foodfacts.core.model.db.DbProductDao;
import com.busgeeth.foodfacts.core.model.entities.Product;
import com.busgeeth.foodfacts.helpers.ProductHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class ProductStore {

    private static final String TAG = ProductStore.class.getSimpleName();

    DbProductDao mProductDao;

    public ProductStore() {
        App app = SharedApplication.getInstance().getApplication();
        mProductDao = app.getDaoSession().getDbProductDao();
    }

    /**
     * Get all the product in the db
     * @return the list of product in the db, an empty list if there is nothing
     */
    public Single<List<Product>> getAllProduct() {
        return Single.defer(() -> {
            List<DbProduct> dbProducts = mProductDao.loadAll();
            if (dbProducts == null) {
                return Single.just(new ArrayList<Product>());
            } else {
               return Observable.fromIterable(dbProducts)
                        .map(ProductHelper::dbToProduct)
                        .toList();
            }
        });
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
