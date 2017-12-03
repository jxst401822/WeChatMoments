package com.john.wechatmoments.base;

/**
 * Created by John on 2017/12/3.
 * View基类
 */
public interface BaseView {

    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    //=======  State  =======
    void stateError();

    void stateLoading();

    void stateMain();
}
