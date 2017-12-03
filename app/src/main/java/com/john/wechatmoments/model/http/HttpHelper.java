package com.john.wechatmoments.model.http;

import com.john.wechatmoments.model.bean.TweetBean;
import com.john.wechatmoments.model.bean.UserBean;

import java.util.List;

import io.reactivex.Flowable;

public interface HttpHelper {

    Flowable<UserBean> getUserInfo(String username);

    Flowable<List<TweetBean>> getTweets(String username);

}
