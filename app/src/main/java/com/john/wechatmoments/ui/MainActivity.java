package com.john.wechatmoments.ui;

import com.john.wechatmoments.R;
import com.john.wechatmoments.base.BaseActivity;
import com.john.wechatmoments.base.contract.MainContract;
import com.john.wechatmoments.presenter.MainPresenter;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showRefreshData() {

    }

    @Override
    public void showLoadMoreData() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
