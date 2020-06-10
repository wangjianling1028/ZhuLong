package com.jiyun.zhulong.model;

import android.content.Context;

import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.interceptor.NetManager;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.mvp.ICommonPresenter;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.Application1907;

import java.util.Map;

public class NewsEliteMolder implements ICommonModel {
    private NetManager netManager = NetManager.getInstance();
    private Context context = Application1907.get07ApplicationContext();

    @Override
    public void getData(ICommonPresenter iCommonPresenter, int apiConfig, int loadTypeConfig, Object[] object) {
        switch (apiConfig){
            case ApiConfig.GET_NEWS_DATA:
                Map<String,Object> map = (Map<String, Object>) object[0];
                netManager.netWork(netManager.getService(context.getString(R.string.bbs_openapi)).getNewsElite(map),iCommonPresenter,apiConfig,loadTypeConfig,object);
                break;
        }
    }
}
