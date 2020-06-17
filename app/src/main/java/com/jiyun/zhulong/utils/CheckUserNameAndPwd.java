package com.jiyun.zhulong.utils;

import android.content.Context;

import com.yiyatech.utils.ext.ToastUtils;


public class CheckUserNameAndPwd {
    /**
     * 验证用户名是否合法
     */
    public static boolean verificationUsername(Context context,String username, String password) {// 验证用户名
        String reg = "^[a-zA-Z0-9_\u4e00-\u9fa5]+$";
        String msg = "用户名为2-6个汉字或2-12个英文字母数字";
        if (username.matches(reg)) {
            if (username.length() == username.getBytes().length) {// 没有汉字
                if (username.getBytes().length > 12
                        || username.getBytes().length < 2) {
                    ToastUtils.show(context,msg);
                    return false;
                } else {
                    return verificationPassword(context,username, password);
                }
            } else {// 有汉字
                int hanzi_num = (username.getBytes().length - username.length()) / 2;
                int length = username.getBytes().length - hanzi_num;
                if (length > 12 || length < 4) {
                    ToastUtils.show(context,msg);
                    return false;
                } else {
                    return  verificationPassword(context,username, password);
                }
            }
        } else {
            ToastUtils.show(context,msg);
            return false;
        }
    }

    /**
     * 验证密码是否合法
     */
    private static boolean verificationPassword(Context context,String username, String password) {
        String reg = "^[a-zA-Z0-9_]{6,18}$";
        String reg1 = "^([0-9]{6,18})$";
        String reg2 = "^([a-zA-Z]{6,18})$";
        String msg = "";
        if (password.matches(reg)) {
            if (password.matches(reg1)) {
                msg = "密码不能全数字";
                ToastUtils.show(context,msg);
                return false;
            }
            if (password.matches(reg2)) {
                msg = "密码不能全字母";
                ToastUtils.show(context,msg);
                return false;
            }
            if (password.startsWith("000000") || password.startsWith("111111")
                    || password.startsWith("222222")
                    || password.startsWith("333333")
                    || password.startsWith("444444")
                    || password.startsWith("555555")
                    || password.startsWith("666666")
                    || password.startsWith("777777")
                    || password.startsWith("888888")
                    || password.startsWith("999999")) {
                msg = "密码前6位不能用相同的数字";
                ToastUtils.show(context,msg);
                return false;
            }
            if (password.equals(username)) {
                msg = "密码不能与用户名相同";
                ToastUtils.show(context,msg);
                return false;
            }
        } else {
            msg = "请输入有效密码，6~18位字母数字或下划线";
            ToastUtils.show(context,msg);
            return false;
        }
        return  true;
    }
}
