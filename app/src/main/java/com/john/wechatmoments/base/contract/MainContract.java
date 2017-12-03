package com.john.wechatmoments.base.contract;


import com.john.wechatmoments.base.BasePresenter;
import com.john.wechatmoments.base.BaseView;
import com.john.wechatmoments.model.bean.WelcomeBean;

/**
 * Created by John on 2017/12/4.
 */

public interface MainContract {
    interface View extends BaseView {

        void showRefreshData();

        void showLoadMoreData();

    }

    interface Presenter extends BasePresenter<View> {

        void loadMoreData();

        void refreshData();

    }
}
