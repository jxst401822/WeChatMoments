package com.john.wechatmoments.ui;

import android.content.Intent;
import android.widget.ImageView;

import com.john.wechatmoments.R;
import com.john.wechatmoments.base.BaseActivity;
import com.john.wechatmoments.base.contract.WelcomeContract;
import com.john.wechatmoments.model.bean.WelcomeBean;
import com.john.wechatmoments.presenter.WelcomePresenter;

import butterknife.BindView;

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View {

    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getWelcomeData();
    }

    @Override
    public void showContent(WelcomeBean welcomeBean) {
        ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
    }

    @Override
    public void jumpToMain() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

