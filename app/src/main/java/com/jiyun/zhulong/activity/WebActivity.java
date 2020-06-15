package com.jiyun.zhulong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jiyun.frame.constants.ConstantKey;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.BaseMvpActiviy;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends BaseMvpActiviy {

    @BindView(R.id.web)
    WebView web;


    @Override
    protected int setLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected ICommonModel setModel() {
        return null;
    }

    @Override
    protected void initView() {
        web = (WebView) findViewById(R.id.web);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        web.loadUrl(url);
        web.setWebViewClient(new WebViewClient());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] objects) {

    }

    @Override
    public void onBackPressed() {
        goToActivity();
    }
}
