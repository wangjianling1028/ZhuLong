package com.jiyun.frame.api;

public class ApiConfig {
    //有时候一个页面可能需要加载多个数据的接口，所以这里标注的是接口的类型
    public static final int LEAD_URL =1;
    public static final int SPECIALTY_URL =2;
    public static final int SUBJECT = 3;
    public static final int SEND_VERIFY = 4;
    public static final int VERIFY_LOGIN = 5;
    public static final int GET_HEADER_INFO = 6;
    public static final int GET_COURSE_DATA = 7;
    //资料小组
    public static final int GET_SQUAD_DATA=8;
    //最新精华
    public static final int GET_NEWS_DATA=9;
    //主页面1
    public static final int GET_HoME1=10;
    //主页面2
    public static final int GET_HoME2=11;
    //关注
    public static final int CLICK_TO_FOCUS = 12;
    public static final int CLICK_CANCEL_FOCUS = 13;
    //注册
    public static final int REGISTER_PHONE = 14;
    public static final int CHECK_PHONE_IS_USED = 15;
    public static final int SEND_REGISTER_VERIFY = 16;
    public static final int NET_CHECK_USERNAME = 17;
    public static final int COMPLETE_REGISTER_WITH_SUBJECT = 18;
    //登录
    public static final int ACCOUNT_LOGIN = 19;
    //vip
    public static final int VIP_BANNER_DATA_INFO = 20;
    public static final int VIP_BOTTOM_DATA_INFO = 21;
    //微信登录
    public static final int GET_WE_CHAT_TOKEN = 20;
    public static final int POST_WE_CHAT_LOGIN_INFO = 21;
    public static final int BIND_ACCOUNT = 22;
    //资料详情
    public static final int GROUP_DETAIL = 23;
    public static final int GROUP_DETAIL_FOOTER_DATA = 24;
    //课程详情
    public static final int COURSE_DETAIL_INFO = 25;
}
