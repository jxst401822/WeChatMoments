package com.john.wechatmoments.model.http.api;

import com.john.wechatmoments.model.bean.TweetBean;
import com.john.wechatmoments.model.bean.UserBean;
import com.john.wechatmoments.model.http.response.TWHttpResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by John on 17/12/3.
 */

public interface TWApis {

    String HOST = "http://thoughtworks-ios.herokuapp.com/";

    /**
     * 获取用户信息
     */
    @GET("user/{username}")
    Flowable<UserBean> getUserInfo(@Path("username") String username);

    /**
     * 获取用户朋友圈数据
     */
    @GET("user/{username}/tweets")
    Flowable<List<TweetBean>> getTweets(@Path("username") String username);

}
