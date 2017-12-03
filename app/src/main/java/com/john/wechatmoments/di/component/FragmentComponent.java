package com.john.wechatmoments.di.component;

import android.app.Activity;


import com.john.wechatmoments.di.module.FragmentModule;
import com.john.wechatmoments.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by John on 17/12/4.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();
}
