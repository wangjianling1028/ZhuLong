package com.jiyun.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 第三方登录信息类
 * Created by huangwy on 2018/2/28.
 * email: kisenhuang@163.com.
 */

public class ThirdLoginData implements Parcelable {

    public String openid;//用户唯一标识
    public String token;//用户凭证
    public String refreshToken;
    public String unionid;
    public long utime;//单位 毫秒
    public final int type;// 1 微博,2 QQ, 3 微信

    public int getType() {
        return type;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public long getUtime() {
        return utime;
    }

    public void setUtime(long utime) {
        this.utime = utime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.openid);
        dest.writeString(this.token);
        dest.writeString(this.refreshToken);
        dest.writeString(this.unionid);
        dest.writeLong(this.utime);
        dest.writeInt(this.type);
    }

    public ThirdLoginData(int type) {
        this.type = type;
    }

    protected ThirdLoginData(Parcel in) {
        this.openid = in.readString();
        this.token = in.readString();
        this.refreshToken = in.readString();
        this.unionid = in.readString();
        this.utime = in.readLong();
        this.type = in.readInt();
    }

    public static final Creator<ThirdLoginData> CREATOR = new Creator<ThirdLoginData>() {
        @Override
        public ThirdLoginData createFromParcel(Parcel source) {
            return new ThirdLoginData(source);
        }

        @Override
        public ThirdLoginData[] newArray(int size) {
            return new ThirdLoginData[size];
        }
    };
}
