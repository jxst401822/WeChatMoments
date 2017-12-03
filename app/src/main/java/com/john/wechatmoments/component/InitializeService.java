package com.john.wechatmoments.component;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.github.moduth.blockcanary.BlockCanary;
import com.john.wechatmoments.app.App;
import com.john.wechatmoments.widget.AppBlockCanaryContext;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;


/**
 * Created by John on 17/12/4.
 */

public class InitializeService extends IntentService {

    private static final String ACTION_INIT = "initApplication";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                initApplication();
            }
        }
    }

    private void initApplication() {
        //初始化日志
        Logger.init(getPackageName()).hideThreadInfo();

        //初始化内存泄漏检测
        LeakCanary.install(App.getInstance());

        //初始化过度绘制检测
        BlockCanary.install(getApplicationContext(), new AppBlockCanaryContext()).start();
    }
}
