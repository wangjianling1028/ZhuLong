package com.jiyun.frame.api;

import java.util.Map;

import com.jiyun.bean.CourseDrillBean;
import com.jiyun.bean.DataSquadBean;
import com.jiyun.bean.NewsEliteBean;
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

}
