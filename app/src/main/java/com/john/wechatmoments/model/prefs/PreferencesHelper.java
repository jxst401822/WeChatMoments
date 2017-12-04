package com.john.wechatmoments.model.prefs;


public interface PreferencesHelper {

    boolean getNightModeState();

    void setNightModeState(boolean state);

    boolean getNoImageState();

    void setNoImageState(boolean state);

    boolean getAutoCacheState();

    void setAutoCacheState(boolean state);

    String  getLoginId();

    void setLoginId(String loginId);

    void setPosition(int position);

    int getPosition();
}
