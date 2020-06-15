package com.jiyun.zhulong.model;


import android.content.Context;

import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.interceptor.NetManager;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.mvp.ICommonPresenter;
import com.jiyun.frame.utils.ParamHashMap;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.Application1907;

import java.util.Map;

public class MainPageModel implements ICommonModel {
    private NetManager netManager = NetManager.getInstance();
    private Context context = Application1907.get07ApplicationContext();

    @Override
    public void getData(ICommonPresenter iCommonPresenter, int apiConfig, int loadTypeConfig, Object[] object) {
        switch (apiConfig){
            case ApiConfig.GET_HoME1:
                Map<String,Object> map = (Map<String, Object>) object[0];
                netManager.netWork(netManager.getService("https://edu.zhulong.com/openapi/").getCommonList(map),iCommonPresenter,apiConfig,loadTypeConfig);
                break;
            case ApiConfig.GET_HoME2:
                Map<String,Object> map1 = (Map<String, Object>) object[0];
                netManager.netWork(netManager.getService("https://edu.zhulong.com/openapi/").getBannerList(map1),iCommonPresenter,apiConfig,loadTypeConfig);
                break;
        }
    }
}
