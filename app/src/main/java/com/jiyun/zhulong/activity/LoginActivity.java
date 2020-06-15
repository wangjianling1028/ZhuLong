package com.jiyun.zhulong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.api.LoadTypeConfig;
import com.jiyun.frame.bean.BaseInfo;
import com.jiyun.frame.bean.LoginInfo;
import com.jiyun.frame.bean.PersonHeader;
import com.jiyun.frame.constants.ConstantKey;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.BaseMvpActiviy;
import com.jiyun.zhulong.design.LoginView;
import com.jiyun.zhulong.model.AccountModel;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseMvpActiviy implements LoginView.LoginViewCallBack {
    @BindView(R.id.login_view)
    LoginView mLoginView;
    @BindView(R.id.close_login)
    ImageView closeLogin;
    @BindView(R.id.login_by_qq)
    ImageView loginByQq;
    @BindView(R.id.login_by_wx)
    ImageView loginByWx;
    @BindView(R.id.third_login_desc)
    TextView thirdLoginDesc;
    @BindView(R.id.register_press)
    TextView registerPress;
    @BindView(R.id.logo_image)
    ImageView logoImage;
    @BindView(R.id.forgot_pwd)
    TextView forgotPwd;
    @BindView(R.id.base_line)
    View baseLine;
    private Disposable mSubscribe;
    private String phoneNum;
    private long time = 60l;
    private String mFromType;


    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected ICommonModel setModel() {
        return new AccountModel();
    }

    @Override
    protected void initView() {
        mFromType = getIntent().getStringExtra("fromType");
        mLoginView.setLoginViewCallBack(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] objects) {
        switch (apiConfig) {
            case ApiConfig.SEND_VERIFY:
                BaseInfo<String> info = (BaseInfo<String>) objects[0];
                if (info.result != null) {
                    showToast(info.result);
                }
                goTime();
                break;

            case ApiConfig.VERIFY_LOGIN:
            case ApiConfig.ACCOUNT_LOGIN:
                BaseInfo<LoginInfo> baseInfo = (BaseInfo<LoginInfo>) objects[0];
                LoginInfo loginInfo = baseInfo.result;
                loginInfo.login_name = phoneNum;
                mApplication.setLoginInfo(loginInfo);
                mPresenter.getData(ApiConfig.GET_HEADER_INFO, LoadTypeConfig.NORMAL);
                break;

            case ApiConfig.GET_HEADER_INFO:
                PersonHeader personHeader = ((BaseInfo<PersonHeader>) objects[0]).result;
                mApplication.getLoginInfo().personHeader = personHeader;
                //将用户信息保存到本地，下次将 不再登录
                SharedPrefrenceUtils.putObject(this, ConstantKey.LOGIN_INFO, mApplication.getLoginInfo());
                showLog("登录成功，-------用户信息：" + SharedPrefrenceUtils.getObject(this, ConstantKey.LOGIN_INFO));
                jump();
                break;

               /* BaseInfo<LoginInfo> baseInfo1 = (BaseInfo<LoginInfo>) objects[0];
                LoginInfo loginInfo1 = baseInfo1.result;
                if (!TextUtils.isEmpty(phoneNum)) loginInfo1.login_name = phoneNum;
                mApplication.setLoginInfo(loginInfo1);
                mPresenter.getData(ApiConfig.GET_HEADER_INFO,LoadTypeConfig.NORMAL);
                break;*/
        }
    }

    //如果已经选择过专业就跳转首页，否则跳转专业选择页
    private void jump() {
        if (SharedPrefrenceUtils.getObject(this, ConstantKey.IS_SELECTDE) != null) {
            startActivity(new Intent(this, HomeActivity.class));
        } else {
            startActivity(new Intent(this, SpecialtyActivity.class));
        }
        this.finish();
    }

    private void goTime() {
        mSubscribe = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(goTime -> {
                    mLoginView.tvSecurityGetAuthcode.setText(time - goTime + "s");
                    if (time - goTime < 1) doPre();
                });
    }

    private void doPre() {
        if (mSubscribe != null && !mSubscribe.isDisposed()) mSubscribe.dispose();
        mLoginView.tvSecurityGetAuthcode.setText("获取验证码");
    }


    @Override
    public void sendVerifyCode(String phoneNum) {
        this.phoneNum = phoneNum;
        mPresenter.getData(ApiConfig.SEND_VERIFY, LoadTypeConfig.NORMAL, phoneNum);
    }

    @Override
    public void loginPress(int type, String userName, String pwd) {
        doPre();
        if (mLoginView.mCurrentLoginType == mLoginView.SECURITY_TYPE)
            mPresenter.getData(ApiConfig.VERIFY_LOGIN, LoadTypeConfig.NORMAL, userName, pwd);
        else {
            mPresenter.getData(ApiConfig.ACCOUNT_LOGIN, LoadTypeConfig.NORMAL, userName, pwd);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doPre();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.close_login, R.id.register_press})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close_login:
                  finish();
                break;
            case R.id.register_press:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}
