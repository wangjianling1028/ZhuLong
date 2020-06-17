package com.jiyun.zhulong.model;

import android.content.Context;

import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.context.FrameApplication;
import com.jiyun.frame.interceptor.NetManager;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.mvp.ICommonPresenter;
import com.jiyun.frame.utils.ParamHashMap;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.Application1907;

import java.util.Map;

public class DataSquadModel implements ICommonModel {

    private NetManager netManager = NetManager.getInstance();
    private Context mContext = Application1907.get07ApplicationContext();
    @Override
    public void getData(ICommonPresenter iCommonPresenter, int apiConfig, int loadTypeConfig, Object[] object) {
        switch (apiConfig){
            case ApiConfig.GET_SQUAD_DATA:
                Map<String,Object> map= (Map<String, Object>) object[0];
                netManager.netWork(netManager.getService(mContext.getString(R.string.bbs_openapi)).getDataSquadData(map),iCommonPresenter,apiConfig,loadTypeConfig,object);
                break;
             case ApiConfig.CLICK_CANCEL_FOCUS:
                // ParamHashMap add = new ParamHashMap().add("group_id", object[0]).add("type", 1).add("screctKey", FrameApplication.getFrameApplicationContext().getString(R.string.secrectKey_posting));
                 Map<String,Object> add = (Map<String, Object>) object[0];
                 int removePosi= (int) object[1];
                    netManager.netWork(netManager.getService("https://bbs.zhulong.com/api/").removeFocus(add),iCommonPresenter,apiConfig,loadTypeConfig,removePosi);
                 break;
             case ApiConfig.CLICK_TO_FOCUS:
               //  ParamHashMap add1 = new ParamHashMap().add("gid", object[0]).add("group_name", object[1]).add("screctKey", FrameApplication.getFrameApplicationContext().getString(R.string.secrectKey_posting));
                 Map<String,Object> add1 = (Map<String, Object>) object[0];
                 int focusPosi= (int) object[1];
                 netManager.netWork(netManager.getService("https://bbs.zhulong.com/api/").focus(add1),iCommonPresenter,apiConfig,loadTypeConfig,focusPosi);
                 break;
            case ApiConfig.GROUP_DETAIL:
                netManager.netWork(netManager.getService("https://bbs.zhulong.com/openapi/").getGroupDetail(object[0]),iCommonPresenter,apiConfig,loadTypeConfig);
                break;
            case ApiConfig.GROUP_DETAIL_FOOTER_DATA:
                netManager.netWork(netManager.getService("https://bbs.zhulong.com/openapi/").getGroupDetailFooterData((Map<String, Object>) object[0]),iCommonPresenter,apiConfig,loadTypeConfig);
                break;
        }

    }
}
