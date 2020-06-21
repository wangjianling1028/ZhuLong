package com.jiyun.zhulong.base;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.jiyun.frame.context.FrameApplication;

public class Application1907 extends FrameApplication {
    private static Application1907 mApplication1907;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication1907 = this;
        MultiDex.install(this);
    }

    public Application1907 getApplication() {
        return mApplication1907;
    }

    public static Context get07ApplicationContext() {
        return mApplication1907.getApplicationContext();
    }

}
