package com.john.wechatmoments.model.event;

/**
 * Created by John on 17/12/4.
 */

public class NightModeEvent {

    private boolean isNightMode;

    public void setNightMode(boolean nightMode) {
        isNightMode = nightMode;
    }

    public boolean getNightMode() {
        return isNightMode;
    }
}
