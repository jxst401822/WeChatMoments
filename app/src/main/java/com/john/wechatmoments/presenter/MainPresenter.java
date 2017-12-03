package com.john.wechatmoments.presenter;

import com.john.wechatmoments.base.RxPresenter;
import com.john.wechatmoments.base.contract.MainContract;
import com.john.wechatmoments.model.db.RealmHelper;
import com.john.wechatmoments.model.http.RetrofitHelper;
import com.john.wechatmoments.model.prefs.ImplPreferencesHelper;
import com.john.wechatmoments.model.prefs.PreferencesHelper;

import javax.inject.Inject;

/**
 * Created by John on 2017/12/4.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private RealmHelper mRealmHelper;
    private ImplPreferencesHelper mReferencesHelper;
    private RetrofitHelper mRetrofitHelper;


    @Inject
    public MainPresenter(RealmHelper realmHelper, ImplPreferencesHelper preferencesHelper, RetrofitHelper retrofitHelper) {
        this.mRealmHelper = realmHelper;
        this.mReferencesHelper = preferencesHelper;
        this.mRetrofitHelper = retrofitHelper;
    }


    @Override
    public void loadMoreData() {

    }

    @Override
    public void refreshData() {

    }
}
