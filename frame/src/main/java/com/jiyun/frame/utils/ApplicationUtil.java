package com.jiyun.frame.utils;

import android.app.Application;
import android.content.Context;

/**
 *    ：      --
 * 创建于： 2020/5/31 09:12
 *    邮箱：1750827655@qq.com
 */
public class ApplicationUtil extends Application {
    private static ApplicationUtil applicationUtil;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationUtil=this;
    }

    public ApplicationUtil getApplicationUtil(){
        return applicationUtil;
    }

    public static Context getAppContext(){
        return mContext.getApplicationContext();
    }
}
