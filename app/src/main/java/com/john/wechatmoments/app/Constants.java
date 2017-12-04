package com.john.wechatmoments.app;

import android.os.Environment;

import com.john.wechatmoments.model.bean.TweetBean;
import com.john.wechatmoments.model.bean.UserBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/12/3.
 */

public class Constants {


    //================= PREFERENCE ====================

    public static final String SP_NIGHT_MODE = "night_mode";

    public static final String SP_NO_IMAGE = "no_image";

    public static final String SP_AUTO_CACHE = "auto_cache";

    public static final String SP_LOGIN_ID = "login_id";

    //================= PATH ====================

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "John" + File.separator + "WeChatMoments";

    //================= UserBean ====================
    public static UserBean userBean;
    public static List<TweetBean> tweetBeanList = new ArrayList<>();
    public static int position = 0;

}
