package com.jiyun.zhulong.base;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;


import com.jiyun.frame.constants.ConstantKey;
import com.jiyun.frame.bean.Device;
import com.jiyun.frame.mvp.CommonPresenter;
import com.jiyun.frame.context.FrameApplication;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.mvp.ICommonView;
import com.jiyun.frame.utils.SystemUtils;
import com.jiyun.zhulong.activity.HomeActivity;
import com.jiyun.zhulong.activity.LoginActivity;
import com.jiyun.zhulong.activity.MyHomeActivity;
import com.jiyun.zhulong.activity.SpecialtyActivity;
import com.yiyatech.utils.NetworkUtils;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import butterknife.ButterKnife;

public abstract class BaseMvpActiviy<M extends ICommonModel> extends BaseActivity implements ICommonView {
    private M mModel;
    public CommonPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        ButterKnife.bind(this);
        mModel = setModel();
        if (mModel != null)
            mPresenter = new CommonPresenter(this, mModel);
        initView();
        initDevice();
        initData();
        initListener();
    }

    protected abstract int setLayout();

    protected abstract M setModel();

    protected abstract void initView();

    protected abstract void initData();

    public void initListener() {
    }

    protected abstract void onSuccess(int apiConfig, int loadTypeConfig, Object[] objects);

    public void onFailed(int apiConfig, Throwable throwable) {

    }

    public void initDevice() {
        Device device = new Device();
        device.setScreenWidth(SystemUtils.getSize(this).x);
        device.setScreenHeight(SystemUtils.getSize(this).y);
        device.setDeviceName(SystemUtils.getDeviceName());
        device.setSystem(SystemUtils.getSystem(this));
        device.setVersion(SystemUtils.getVersion(this));
        device.setDeviceId(SystemUtils.getDeviceId(this));
        device.setLocalIp(NetworkUtils.getLocalIpAddress());
        FrameApplication.getFrameApplication().setDeviceInfo(device);
    }

    //跳转页面
    public void goToActivity() {
        if (mApplication.isLogin()) {
            if (SharedPrefrenceUtils.getObject(this, ConstantKey.IS_SELECTDE) != null) {
                startActivity(new Intent(this, MyHomeActivity.class));
            } else {
                startActivity(new Intent(this, SpecialtyActivity.class));
            }
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }

    @Override
    public void netSuccess(int apiConfig, int loadTypeConfig, Object... object) {
        onSuccess(apiConfig, loadTypeConfig, object);
    }

    @Override
    public void netFailed(int apiConfig, Throwable throwable) {
        showLog("错误：" + apiConfig + "，error content" + throwable != null && !TextUtils.isEmpty(throwable.getMessage()) ? throwable.getMessage() : "不明错误类型");
        onFailed(apiConfig, throwable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.clear();
    }
}
