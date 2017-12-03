package com.john.wechatmoments.di.module;

import android.app.Activity;


import com.john.wechatmoments.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by John on 17/12/4.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
