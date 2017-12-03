package com.john.wechatmoments.model.http;

import com.john.wechatmoments.model.bean.TweetBean;
import com.john.wechatmoments.model.bean.UserBean;
import com.john.wechatmoments.model.http.api.TWApis;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class RetrofitHelper implements HttpHelper {

    private TWApis mTWApiService;

    @Inject
    public RetrofitHelper(TWApis twApisService) {
        this.mTWApiService = twApisService;
    }

    @Override
    public Flowable<UserBean> getUserInfo(String username) {
        return mTWApiService.getUserInfo(username);
    }

    @Override
    public Flowable<List<TweetBean>> getTweets(String username) {
        return mTWApiService.getTweets(username);
    }
}
