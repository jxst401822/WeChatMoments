package com.john.wechatmoments.di.component;


import com.john.wechatmoments.app.App;
import com.john.wechatmoments.di.module.AppModule;
import com.john.wechatmoments.di.module.HttpModule;
import com.john.wechatmoments.model.db.RealmHelper;
import com.john.wechatmoments.model.http.RetrofitHelper;
import com.john.wechatmoments.model.prefs.ImplPreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by John on 17/12/4.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类

    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类
}
