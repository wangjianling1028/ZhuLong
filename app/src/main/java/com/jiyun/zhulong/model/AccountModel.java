package com.jiyun.zhulong.model;

import android.content.Context;

import com.jiyun.bean.ThirdLoginData;
import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.constants.ConstantKey;
import com.jiyun.frame.context.FrameApplication;
import com.jiyun.frame.interceptor.NetManager;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.mvp.ICommonPresenter;
import com.jiyun.frame.utils.ParamHashMap;
import com.jiyun.frame.utils.RsaUtil;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.Application1907;

public class AccountModel implements ICommonModel {
    private NetManager mManager = NetManager.getInstance();
    private Context mContext = Application1907.get07ApplicationContext();

    @Override
    public void getData(ICommonPresenter iCommonPresenter, int apiConfig, int loadTypeConfig, Object[] object) {
        switch (apiConfig) {
            case ApiConfig.SEND_VERIFY:
                mManager.netWork(mManager.getService(mContext.getString(R.string.passport_openapi_user)).getLoginVerify((String) object[0]), iCommonPresenter, apiConfig, loadTypeConfig, object);
                break;
            case ApiConfig.VERIFY_LOGIN:
                mManager.netWork(mManager.getService(mContext.getString(R.string.passport_openapi_user)).loginByVerify(new ParamHashMap().add("mobile", object[0]).add("code", object[1])), iCommonPresenter, apiConfig, loadTypeConfig, object);
                break;
            case ApiConfig.GET_HEADER_INFO:
                String uid = FrameApplication.getFrameApplication().getLoginInfo().getUid();
                mManager.netWork(mManager.getService(mContext.getString(R.string.passport_api)).getHeaderInfo(new ParamHashMap().add("zuid", uid).add("uid", uid)), iCommonPresenter, apiConfig, loadTypeConfig, object);
                break;

            //注册
            case ApiConfig.REGISTER_PHONE:
                ParamHashMap add = new ParamHashMap().add("mobile", object[0]).add("code", object[1]);
                mManager.netWork(mManager.getService("https://passport.zhulong.com/api/").checkVerifyCode(add), iCommonPresenter, apiConfig, loadTypeConfig, object);
                break;

            case ApiConfig.CHECK_PHONE_IS_USED:
                mManager.netWork(mManager.getService("https://passport.zhulong.com/api/").checkPhoneIsUsed(object[0]), iCommonPresenter, apiConfig, loadTypeConfig, object);
                break;
            case ApiConfig.SEND_REGISTER_VERIFY:
                mManager.netWork(mManager.getService("https://passport.zhulong.com/api/").sendRegisterVerify(object[0]), iCommonPresenter, apiConfig, loadTypeConfig, object);
                break;

            case ApiConfig.NET_CHECK_USERNAME:
                mManager.netWork(mManager.getService("https://passport.zhulong.com/").checkName(object[0]), iCommonPresenter, loadTypeConfig, apiConfig, object);
                break;

            case ApiConfig.COMPLETE_REGISTER_WITH_SUBJECT:
                ParamHashMap add1 = new ParamHashMap().add("username", object[0]).add("password", RsaUtil.encryptByPublic((String) object[1]))
                        .add("tel", object[2]).add("specialty_id", FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id())
                        .add("province_id", 0).add("city_id", 0).add("sex", 0).add("from_reg_name", 0).add("from_reg", 0);
                mManager.netWork(mManager.getService("https://passport.zhulong.com/api/").registerCompleteWithSubject(add1), iCommonPresenter, loadTypeConfig, apiConfig, object);
                break;

            case ApiConfig.ACCOUNT_LOGIN:
                ParamHashMap deng = new ParamHashMap().add("ZLSessionID", "").add("seccode", "").add("loginName", object[0])
                        .add("passwd", RsaUtil.encryptByPublic((String) object[1])).add("cookieday", "")
                        .add("fromUrl", "android").add("ignoreMobile", "0");
                mManager.netWork(mManager.getService("https://passport.zhulong.com/openapi/").loginByAccount(deng), iCommonPresenter, apiConfig, loadTypeConfig);
                break;
                //微信登录
            case ApiConfig.GET_WE_CHAT_TOKEN:
                ParamHashMap wxParams = new ParamHashMap().add("appid", ConstantKey.WX_APP_ID).add("secret", ConstantKey.WX_APP_SECRET).add("code", object[0]).add("grant_type", "authorization_code");
                mManager.netWork(mManager.getService("https://api.weixin.qq.com/sns/oauth2/").getWechatToken(wxParams),iCommonPresenter,apiConfig,loadTypeConfig);
                break;

            case ApiConfig.POST_WE_CHAT_LOGIN_INFO:
                ThirdLoginData data = (ThirdLoginData) object[0];
                ParamHashMap add2 = new ParamHashMap().add("openid", data.openid).add("type", data.type).add("url", "android");
                mManager.netWork(mManager.getService("https://api.weixin.qq.com/sns/oauth2/").loginByAccounts(add2),iCommonPresenter,apiConfig,loadTypeConfig);
                break;

            case ApiConfig.BIND_ACCOUNT:
                String accout = (String) object[0];
                String password = (String) object[1];
                ThirdLoginData thirdLoginData = (ThirdLoginData) object[2];
                ParamHashMap thirdDataParam = new ParamHashMap().add("username", accout).add("password", RsaUtil.encryptByPublic(password))
                        .add("openid", thirdLoginData.openid).add("t_token", thirdLoginData.token)
                        .add("utime", thirdLoginData.utime).add("type", thirdLoginData.type)
                        .add("url", "android").add("state", 1);
                mManager.netWork(mManager.getService("https://passport.zhulong.com/api/").bindThirdAccount(thirdDataParam),iCommonPresenter,apiConfig,loadTypeConfig);

                break;
        }
    }
}
