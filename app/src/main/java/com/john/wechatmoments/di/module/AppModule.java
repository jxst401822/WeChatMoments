package com.john.wechatmoments.di.module;


import com.john.wechatmoments.app.App;
import com.john.wechatmoments.model.db.DBHelper;
import com.john.wechatmoments.model.db.RealmHelper;
import com.john.wechatmoments.model.http.HttpHelper;
import com.john.wechatmoments.model.http.RetrofitHelper;
import com.john.wechatmoments.model.prefs.ImplPreferencesHelper;
import com.john.wechatmoments.model.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by John on 17/12/4.
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }
}
