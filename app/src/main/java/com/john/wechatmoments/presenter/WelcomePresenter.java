package com.john.wechatmoments.presenter;

import com.john.wechatmoments.R;
import com.john.wechatmoments.app.App;
import com.john.wechatmoments.app.Constants;
import com.john.wechatmoments.base.RxPresenter;
import com.john.wechatmoments.base.contract.WelcomeContract;
import com.john.wechatmoments.component.ACache;
import com.john.wechatmoments.model.bean.TweetBean;
import com.john.wechatmoments.model.bean.UserBean;
import com.john.wechatmoments.model.bean.WelcomeBean;
import com.john.wechatmoments.model.db.RealmHelper;
import com.john.wechatmoments.model.http.RetrofitHelper;
import com.john.wechatmoments.model.prefs.ImplPreferencesHelper;
import com.john.wechatmoments.util.RxUtil;
import com.john.wechatmoments.widget.CommonSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by John on 2017/5/16.
 */

public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter {

    private static final int COUNT_DOWN_TIME = 2200;

    private WelcomeBean welcomeBean = new WelcomeBean(R.drawable.welcome);
    private RealmHelper mRealmHelper;
    private ImplPreferencesHelper mReferencesHelper;
    private RetrofitHelper mRetrofitHelper;


    @Inject
    public WelcomePresenter(RealmHelper realmHelper, ImplPreferencesHelper preferencesHelper, RetrofitHelper retrofitHelper) {
        this.mRealmHelper = realmHelper;
        this.mReferencesHelper = preferencesHelper;
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getWelcomeData() {
        addSubscribe(Flowable.just(welcomeBean)
                .compose(RxUtil.<WelcomeBean>rxSchedulerHelper())
                .subscribe(new Consumer<WelcomeBean>() {
                    @Override
                    public void accept(WelcomeBean welcomeBean) {
                        mView.showContent(welcomeBean);
                        startCountDown();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        jumpToMain();
                    }
                })
        );
    }

    private void startCountDown() {
        addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                        .compose(RxUtil.<Long>rxSchedulerHelper())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) {
                                jumpToMain();
//                        if (mReferencesHelper.getLoginId().isEmpty()) {
//                        } else {
//                            mView.jumpToMain();
//                        }
                            }
                        })
        );
    }

    private void jumpToMain() {

        addSubscribe(mRetrofitHelper.getUserInfo("jsmith")
                .compose(RxUtil.<UserBean>handleResult())
                .compose(RxUtil.<UserBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<UserBean>(mView) {
                    @Override
                    public void onNext(UserBean userBean) {
                        ACache.get(App.getInstance().getApplicationContext()).put(Constants.ACHE_USER, userBean);
                        mReferencesHelper.setPosition(0);
                        mView.jumpToMain();
                    }
                })
        );

    }


}
