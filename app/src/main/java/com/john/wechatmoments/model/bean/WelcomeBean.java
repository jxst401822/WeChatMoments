package com.john.wechatmoments.model.bean;

/**
 * Created by John on 17/12/4.
 */

public class WelcomeBean {

    private String text;
    private String img;

    private int localImg;

    public WelcomeBean(int localImg) {
        this.localImg = localImg;
    }

    public int getLocalImg() {
        return localImg;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setLocalImg(int img) {
        this.localImg = localImg;
    }
}
