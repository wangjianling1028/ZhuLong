package com.jiyun.zhulong.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.api.LoadTypeConfig;
import com.jiyun.frame.bean.LeadBean;
import com.jiyun.frame.bean.LoginInfo;
import com.jiyun.frame.bean.SpecialtyBean;
import com.jiyun.frame.constants.ConstantKey;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.utils.ParamHashMap;
import com.jiyun.frame.utils.SystemUtils;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.BaseMvpActiviy;
import com.jiyun.zhulong.model.TestModel;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import butterknife.BindView;

public class MainActivity extends BaseMvpActiviy {

    @BindView(R.id.time_view)
    TextView mTextView;
    @BindView(R.id.img_home_top)
    ImageView mHomeTopImg;
    @BindView(R.id.img_home_bottom)
    ImageView mHomeBottomImg;
    @BindView(R.id.rl_main)
    RelativeLayout rl;
    private int time = 5;
    private boolean isClick;
    private boolean isDestroy;
    private int widthPixels;
    private int heightPixels;
    private String jump_url;
    private String info_url;

    private SpecialtyBean.ResultBean.DataBean serializable;

    Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (message.what == 0) {
                mTextView.setText("跳过" + time);
                time--;
                mHomeBottomImg.setVisibility(View.VISIBLE);
                int height = mHomeBottomImg.getHeight();
                mHomeTopImg.setMaxHeight(heightPixels - height);
                if (time == 0) {
                    goToActivity();
                }
            }
            return false;
        }
    });
    private LeadBean bean;

    @Override
    protected int setLayout() {
        return R.layout.activity_home_main;
    }

    @Override
    protected ICommonModel setModel() {
        return new TestModel();
    }

    @Override
    protected void initView() {
        //占满屏幕
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //获取手机宽像素和高像素
        getScreenWH();
        //设置状态栏为深灰色
       // getWindow().setStatusBarColor(Color.DKGRAY);
        Glide.with(this).load(R.mipmap.ic_splash).into(mHomeTopImg);
    }

    @Override
    protected void initData() {
        serializable = SharedPrefrenceUtils.getObject(this, ConstantKey.SUBJECT_SELECT);
        String specialtyId = "";
        if (serializable != null && !TextUtils.isEmpty(serializable.getSpecialty_id())) {
            mApplication.setSelectedInfo(serializable);
            specialtyId = serializable.getSpecialty_id();
        }
//        //用来获取手机信息
        Point realSize = SystemUtils.getRealSize(this);
        showLog("x:" + realSize.x + ",y:" + realSize.y);

        ParamHashMap map = new ParamHashMap().add("positions_id", "APP_QD_01").add("is_show", 0).add("w", widthPixels).add("h", heightPixels).add("specialty_id", specialtyId);
        //去P层获取广告数据
        mPresenter.getData(ApiConfig.LEAD_URL, LoadTypeConfig.NORMAL, map);


        //如果3秒内没有获取到广告数据就跳转
        new Handler().postDelayed(() -> {
            if (bean == null) goToActivity();
        }, 3000);
        //将用户信息获取出来设置给loginingo
        if (SharedPrefrenceUtils.getObject(this, ConstantKey.LOGIN_INFO) != null) {
            LoginInfo loginInfo = SharedPrefrenceUtils.getObject(this, ConstantKey.LOGIN_INFO);
            if (loginInfo != null && !TextUtils.isEmpty(loginInfo.getUid()))
                mApplication.setLoginInfo(loginInfo);
        }
    }

    //获取数据成功
    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] objects) {
        switch (apiConfig) {
            case ApiConfig.LEAD_URL:
                if (loadTypeConfig == LoadTypeConfig.NORMAL) {
                    bean = (LeadBean) objects[0];
                    info_url = bean.getResult().getInfo_url();
                    jump_url = bean.getResult().getJump_url();
                    //如果本界面已经销毁不再加载
                    if (!isDestroy && info_url != null && !TextUtils.isEmpty(info_url)) {
                        Glide.with(MainActivity.this).load(info_url).into(mHomeTopImg);
                    }
                    //启用一个线程来做倒计时
                    countDown();
                }
                break;
        }
    }

    private void countDown() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void initListener() {
        super.initListener();
        //广告点击
        mHomeTopImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //在广告没有加载到imageview上时，不予许点击，且只有第一次点击有效
                if (jump_url != null && time < 5 && !isClick) {
                    time = -2;
                    Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                    intent.putExtra("url", jump_url);
                    startActivity(intent);
                    isClick = true;
                    finish();
                }
            }
        });
        //跳转按钮点击
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClick) {
                    time = -2;
                    isClick = true;
                    goToActivity();
                }
            }
        });
    }

    //获取屏幕的宽和高
    private void getScreenWH() {
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        heightPixels = displayMetrics.heightPixels;
        widthPixels = displayMetrics.widthPixels;
        float xdpi = displayMetrics.xdpi;
        float ydpi = displayMetrics.ydpi;
        Log.e("手机信息----", "高：" + heightPixels + ",宽：" + widthPixels + ",xdpi:" + xdpi + ",ydpi:" + ydpi);
    }

    //在本页面销毁时给isDestroy设为true，用来在销毁时将加载资源的功能停止掉
    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroy = true;
    }
}
