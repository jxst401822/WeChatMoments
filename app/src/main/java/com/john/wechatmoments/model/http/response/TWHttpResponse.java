package com.john.wechatmoments.model.http.response;

/**
 * Created by codeest on 16/8/28.
 */

public class TWHttpResponse<T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
