package com.john.wechatmoments.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;


import com.john.wechatmoments.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by John on 17/12/4.
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
