package com.jiyun.zhulong.model;

import android.content.Context;
import android.util.Log;

import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.mvp.ICommonPresenter;
import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.interceptor.NetManager;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.Application1907;

/**
 *    ：      --
 * 创建于： 2020/6/2 21:00
 *    邮箱：1750827655@qq.com
 */
public class SpecialtyModel implements ICommonModel {
    private NetManager mManager = NetManager.getInstance();
    private Context context = Application1907.get07ApplicationContext();

    @Override
    public void getData(ICommonPresenter iCommonPresenter, int apiConfig, int loadTypeConfig, Object[] object) {
        switch (apiConfig) {
            case ApiConfig.SPECIALTY_URL:
                mManager.netWork(mManager.getService(context.getString(R.string.edu_openapi)).getSpecialtyData(), iCommonPresenter, apiConfig, loadTypeConfig, object);
                break;
        }
    }
}
