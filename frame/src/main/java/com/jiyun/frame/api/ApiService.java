package com.jiyun.frame.api;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.jiyun.bean.CourseDrillBean;
import com.jiyun.bean.DataSquadBean;
import com.jiyun.bean.GroupDetailEntity;
import com.jiyun.bean.IndexCommondEntity;
import com.jiyun.bean.NewsEliteBean;
import com.jiyun.bean.VIPBannerBean;
import com.jiyun.bean.VIPBottomDataBean;
import com.jiyun.frame.bean.BaseInfo;
import com.jiyun.frame.bean.LeadBean;
import com.jiyun.frame.bean.FuliBean;
import com.jiyun.frame.bean.LoginInfo;
import com.jiyun.frame.bean.PersonHeader;
import com.jiyun.frame.bean.SpecialtyBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {
    @FormUrlEncoded
    @POST(".")
    //c=api&a=getList&page_id=0
    Observable<FuliBean> getFuliData(@FieldMap Map<String, Object> map, @Field("page_id") int pageId);

    @FormUrlEncoded
    @POST("ad/getAd")
    Observable<LeadBean> getLeadData(@FieldMap Map<String,Object>map);

    @GET("lesson/getAllspecialty")
    Observable<SpecialtyBean>getSpecialtyData();

    @GET("loginByMobileCode")
    Observable<BaseInfo<String>> getLoginVerify(@Query("mobile") String mobile);

    @GET("loginByMobileCode")
    Observable<BaseInfo<LoginInfo>> loginByVerify(@QueryMap Map<String, Object> params);

    @POST("getUserHeaderForMobile")
    @FormUrlEncoded
    Observable<BaseInfo<PersonHeader>> getHeaderInfo(@FieldMap Map<String,Object> params);

    @GET("lesson/getLessonListForApi")//课程训练营
    Observable<CourseDrillBean>getCourseDrillData(@QueryMap Map<String,Object>map);

    @GET("group/getGroupList")//资料小组
    Observable<DataSquadBean> getDataSquadData(@QueryMap Map<String,Object>map);

    @GET("group/getThreadEssence") //最新精华
    Observable<NewsEliteBean> getNewsElite(@QueryMap Map<String,Object>map);



    @POST("lesson/getIndexCommend")//主页面1
    @FormUrlEncoded
    Observable<BaseInfo<List<IndexCommondEntity>>> getCommonList(@FieldMap Map<String,Object> map);


    @POST("lesson/getCarouselphoto")//banner
    @FormUrlEncoded
    Observable<JsonObject> getBannerList(@FieldMap Map<String,Object> map);

    //不关注  已关注
    @POST("removeGroup")
    @FormUrlEncoded
    Observable<BaseInfo>  removeFocus( @FieldMap Map<String,Object> params);

    @POST("joingroup")
    @FormUrlEncoded
    Observable<BaseInfo> focus( @FieldMap Map<String,Object> params);

    //注册

    @POST("checkMobileCode")
    @FormUrlEncoded
    Observable<BaseInfo> checkVerifyCode(@FieldMap Map<String,Object> parame);

    @POST("checkMobileIsUse")
    @FormUrlEncoded
    Observable<BaseInfo> checkPhoneIsUsed(@Field("mobile")Object mobile);

    @POST("sendMobileCode")
    @FormUrlEncoded
    Observable<BaseInfo> sendRegisterVerify(@Field("mobile")Object mobile);

    @POST("user/usernameIsExist")
    @FormUrlEncoded
    Observable<BaseInfo> checkName(@Field("username") Object mobile);

    @POST("userRegForSimple")
    @FormUrlEncoded
    Observable<BaseInfo> registerCompleteWithSubject(@FieldMap Map<String,Object> params);

    @POST("user/userLoginNewAuth")
    @FormUrlEncoded
    Observable<BaseInfo<LoginInfo>> loginByAccount(@FieldMap Map<String,Object> params);

    //vip
    @GET("lesson/get_new_vip")
    Observable<VIPBannerBean> getVIPBannerData();

    @GET("lesson/getVipSmallLessonList")
    Observable<VIPBottomDataBean> getVIPBottomData(@QueryMap Map<String,Object> map);
    //微信登录
    @GET("access_token")
    Observable<JsonObject> getWechatToken(@QueryMap Map<String,Object> map);

    @POST("thirdlogin")
    @FormUrlEncoded
    Observable<BaseInfo<LoginInfo>> loginByAccounts(@FieldMap Map<String,Object> map);

    @POST("newThirdbind")
    @FormUrlEncoded
    Observable<BaseInfo> bindThirdAccount(@FieldMap Map<String,Object> map);

    //资料详情
    @GET("group/getGroupThreadList")
    Observable<BaseInfo<GroupDetailEntity>> getGroupDetail(@Query("gid") Object object);

    @GET("group/getGroupThreadList")
    Observable<JsonObject> getGroupDetailFooterData(@QueryMap Map<String,Object> parmas);
}
