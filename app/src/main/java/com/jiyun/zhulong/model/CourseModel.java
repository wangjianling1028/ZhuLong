package com.jiyun.zhulong.model;

import android.content.Context;

import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.interceptor.NetManager;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.mvp.ICommonPresenter;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.Application1907;

import java.util.Map;

/**
 *    ：      --
 * 创建于： 2020/6/5 23:13
 *    邮箱：1750827655@qq.com
 */
public class CourseModel implements ICommonModel {
    private NetManager mManager=NetManager.getInstance();
    private Context context= Application1907.get07ApplicationContext();
    @Override
    public void getData(ICommonPresenter iCommonPresenter, int apiConfig, int loadTypeConfig, Object[] object) {
        switch (apiConfig){
            case ApiConfig.GET_COURSE_DATA:
               Map<String,Object> map= (Map<String, Object>) object[0];
               mManager.netWork(mManager.getService(context.getString(R.string.edu_openapi)).getCourseDrillData(map),iCommonPresenter,apiConfig,loadTypeConfig,object);
                break;
        }
    }
}
