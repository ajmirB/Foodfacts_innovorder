package com.busgeeth.foodfacts;

import android.app.Application;

import com.busgeeth.foodfacts.core.model.db.DaoMaster;
import com.busgeeth.foodfacts.core.model.db.DaoSession;
import com.facebook.stetho.Stetho;

import org.greenrobot.greendao.database.Database;


public class App extends Application {

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
        SharedApplication.initInstance(this);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "foodfact-db");
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
