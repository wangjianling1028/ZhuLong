package com.jiyun.zhulong.model;

import android.content.Context;

import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.interceptor.NetManager;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.mvp.ICommonPresenter;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.Application1907;

import java.util.Map;

public class VipModel implements ICommonModel {
    NetManager manager = NetManager.getInstance();
    @Override
    public void getData(ICommonPresenter iCommonPresenter, int apiConfig, int loadTypeConfig, Object[] object) {
        switch (apiConfig){
            case ApiConfig.VIP_BANNER_DATA_INFO:
                manager.netWork(manager.getService("https://edu.zhulong.com/openapi/").getVIPBannerData(),iCommonPresenter,apiConfig,loadTypeConfig);
                break;

            case ApiConfig.VIP_BOTTOM_DATA_INFO:
                Map<String,Object> map = (Map<String, Object>) object[0];
                manager.netWork(manager.getService("https://edu.zhulong.com/openapi/").getVIPBottomData(map),iCommonPresenter,apiConfig,loadTypeConfig);
                break;
        }
    }
}
