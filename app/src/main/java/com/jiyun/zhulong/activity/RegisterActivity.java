package com.jiyun.zhulong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.api.LoadTypeConfig;
import com.jiyun.frame.bean.BaseInfo;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.BaseMvpActiviy;
import com.jiyun.zhulong.model.AccountModel;
import com.jiyun.zhulong.mypackage.MyTextWatcher;
import com.yiyatech.utils.newAdd.RegexUtil;
import com.yiyatech.utils.newAdd.SoftInputControl;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import razerdp.design.DialogPopup;

public class RegisterActivity extends BaseMvpActiviy implements DialogPopup.DialogClickCallBack {


    @BindView(R.id.title_content)
    TextView titleContent;
    @BindView(R.id.telephone_desc)
    TextView telephoneDesc;
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.getVerification)
    TextView getVerification;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.next_page)
    TextView nextPage;
    private Disposable mTimeObserver;
    private DialogPopup mConfirmDialog;

    @Override
    protected int setLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected ICommonModel setModel() {
        return new AccountModel();
    }

    @Override
    protected void initView() {
        titleContent.setText("填写手机号码");
        nextPage.setEnabled(false);
        password.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                nextPage.setEnabled(s.length() == 6 ? true : false);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] objects) {
        switch (apiConfig){
            case ApiConfig.CHECK_PHONE_IS_USED:
                BaseInfo baseInfo = (BaseInfo) objects[0];
                if (baseInfo.isSuccess()){
                    mConfirmDialog = new DialogPopup(this, true);
                    mConfirmDialog.setContent(userName.getText().toString() + "\n"+ "是否将短信发送到该手机");
                    mConfirmDialog.setDialogClickCallBack(this);
                    mConfirmDialog.showPopupWindow();
                }else{
                    showToast("该手机已注册");
                }
                break;
            case ApiConfig.SEND_REGISTER_VERIFY:
                BaseInfo sendResult = (BaseInfo) objects[0];
                if (sendResult.isSuccess()){
                    showToast("验证码发送成功");
                    goTime();
                }else showLog(sendResult.msg);
                break;
            case ApiConfig.REGISTER_PHONE:
                BaseInfo info = (BaseInfo) objects[0];
                if (info.isSuccess()){
                    showToast("验证码验证成功");
                    startActivity(new Intent(this,RegisterPhoneActivity.class).putExtra("phoneNum",telephoneDesc.getText().toString()+userName.getText().toString().trim()));
                }else showLog(info.msg);
                break;
        }
    }
    private int preTime = 60;
    private void goTime() {
        mTimeObserver = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(l -> {
                    if (preTime - l > 0) {
                        getVerification.setText(preTime - l + "s");
                    } else {
                        getVerification.setText("获取验证码");
                        disPose();
                    }
                });
    }

    private void disPose() {
        if (mTimeObserver != null && !mTimeObserver.isDisposed()) mTimeObserver.dispose();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_image, R.id.getVerification, R.id.next_page})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                finish();
                break;
            case R.id.getVerification:
                if (userName.getText() == null){
                    showToast("输入手机号码");
                    return;
                }
                boolean phone = RegexUtil.isPhone(userName.getText().toString().trim());
                if (phone){
                    SoftInputControl.hideSoftInput(this,userName);
                    mPresenter.getData(ApiConfig.CHECK_PHONE_IS_USED, LoadTypeConfig.NORMAL,telephoneDesc.getText().toString() + userName.getText().toString().trim());
                }else showToast("手机格式错误");
                break;
            case R.id.next_page:
                mPresenter.getData(ApiConfig.REGISTER_PHONE,LoadTypeConfig.NORMAL,telephoneDesc.getText().toString() + userName.getText().toString().trim(),password.getText().toString().trim());
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        disPose();
    }

    @Override
    public void onClick(int type) {
        if (type == mConfirmDialog.OK){
            mPresenter.getData(ApiConfig.SEND_REGISTER_VERIFY,LoadTypeConfig.NORMAL,telephoneDesc.getText().toString() + userName.getText().toString().trim());
        }
        mConfirmDialog.dismiss();
    }
}