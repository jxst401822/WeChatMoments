package com.john.wechatmoments.base.contract;


import com.john.wechatmoments.base.BasePresenter;
import com.john.wechatmoments.base.BaseView;
import com.john.wechatmoments.model.bean.TweetBean;
import com.john.wechatmoments.model.bean.WelcomeBean;

import java.util.List;

/**
 * Created by John on 2017/12/4.
 */

public interface MainContract {
    interface View extends BaseView {

        void showRefreshData(List<TweetBean> list);

        void showLoadMoreData();

    }

    interface Presenter extends BasePresenter<View> {

        void loadMoreData();

        void refreshData();

    }
}
