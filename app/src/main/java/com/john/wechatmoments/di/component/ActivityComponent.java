package com.john.wechatmoments.di.component;

import android.app.Activity;


import com.john.wechatmoments.base.contract.MainContract;
import com.john.wechatmoments.di.module.ActivityModule;
import com.john.wechatmoments.di.scope.ActivityScope;
import com.john.wechatmoments.ui.MainActivity;
import com.john.wechatmoments.ui.WelcomeActivity;

import dagger.Component;

/**
 * Created by John on 17/12/4.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(WelcomeActivity welcomeActivity);

    void inject(MainActivity mainActivity);
}
