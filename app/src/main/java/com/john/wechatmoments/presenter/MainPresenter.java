package com.john.wechatmoments.presenter;

import com.google.gson.Gson;
import com.john.wechatmoments.app.Constants;
import com.john.wechatmoments.base.RxPresenter;
import com.john.wechatmoments.base.contract.MainContract;
import com.john.wechatmoments.model.bean.TweetBean;
import com.john.wechatmoments.model.db.RealmHelper;
import com.john.wechatmoments.model.http.RetrofitHelper;
import com.john.wechatmoments.model.prefs.ImplPreferencesHelper;
import com.john.wechatmoments.model.prefs.PreferencesHelper;
import com.john.wechatmoments.util.RxUtil;
import com.john.wechatmoments.widget.CommonSubscriber;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.RealmObject;

/**
 * Created by John on 2017/12/4.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private RealmHelper mRealmHelper;
    private ImplPreferencesHelper mReferencesHelper;
    private RetrofitHelper mRetrofitHelper;

    private String json = " {\n" +
            "    \"content\": \"沙发！\",\n" +
            "    \"images\": [\n" +
            "      {\n" +
            "        \"url\": \"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRDy7HZaHxn15wWj6pXE4uMKAqHTC_uBgBlIzeeQSj2QaGgUzUmHg\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"url\": \"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTlJRALAf-76JPOLohBKzBg8Ab4Q5pWeQhF5igSfBflE_UYbqu7\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"url\": \"http://i.ytimg.com/vi/rGWI7mjmnNk/hqdefault.jpg\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"sender\": {\n" +
            "      \"username\": \"jport\",\n" +
            "      \"nick\": \"Joe Portman\",\n" +
            "      \"avatar\": \"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRJm8UXZ0mYtjv1a48RKkFkdyd4kOWLJB0o_l7GuTS8-q8VF64w\"\n" +
            "    },\n" +
            "    \"comments\": [\n" +
            "      {\n" +
            "        \"content\": \"Good.\",\n" +
            "        \"sender\": {\n" +
            "          \"username\": \"outman\",\n" +
            "          \"nick\": \"Super hero\",\n" +
            "          \"avatar\": \"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRJm8UXZ0mYtjv1a48RKkFkdyd4kOWLJB0o_l7GuTS8-q8VF64w\"\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"content\": \"Like it too\",\n" +
            "        \"sender\": {\n" +
            "          \"username\": \"inman\",\n" +
            "          \"nick\": \"Doggy Over\",\n" +
            "          \"avatar\": \"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRJm8UXZ0mYtjv1a48RKkFkdyd4kOWLJB0o_l7GuTS8-q8VF64w\"\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  }";


    @Inject
    public MainPresenter(RealmHelper realmHelper, ImplPreferencesHelper preferencesHelper, RetrofitHelper retrofitHelper) {
        this.mRealmHelper = realmHelper;
        this.mReferencesHelper = preferencesHelper;
        this.mRetrofitHelper = retrofitHelper;
    }


    @Override
    public void loadMoreData(int count) {
        List<TweetBean> tweetBeans = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (Constants.position == Constants.tweetBeanList.size()) {
                break;
            }
            tweetBeans.add(Constants.tweetBeanList.get(Constants.position));
            Constants.position++;
        }
        mView.showLoadMoreData(tweetBeans);
    }

    @Override
    public void refreshData(final int count) {
        addSubscribe(mRetrofitHelper.getTweets("jsmith")
                .compose(RxUtil.<List<TweetBean>>handleResult())
                .compose(RxUtil.<List<TweetBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<List<TweetBean>>(mView) {
                    @Override
                    public void onNext(List<TweetBean> list) {
                        List<TweetBean> errorTweetBeans = new ArrayList<>();
                        for (TweetBean tweetBean : list) {
                            if (tweetBean.getError() != null || tweetBean.getContent() == null || tweetBean.get_$UnknownError287() != null) {
                                errorTweetBeans.add(tweetBean);
                            }
                        }
                        list.removeAll(errorTweetBeans);
                        Constants.tweetBeanList.addAll(list);
                        mReferencesHelper.setLoginId("jsmith");
                        List<TweetBean> tweetBeans = new ArrayList<>();
                        tweetBeans.addAll(Constants.tweetBeanList);
//                        for (int i = 0; i < count; i++) {
//                            if (Constants.position == Constants.tweetBeanList.size() - 1) {
//                                break;
//                            }
//                            tweetBeans.add(Constants.tweetBeanList.get(Constants.position));
//                            Constants.position++;
//                        }
                        mView.showRefreshData(tweetBeans);
                    }
                })
        );

    }
}
