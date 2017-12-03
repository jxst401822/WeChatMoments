package com.john.wechatmoments.base;

/**
 * Created by John on 2017/12/3.
 * Presenter基类
 */
public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
