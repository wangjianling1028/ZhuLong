package com.jiyun.zhulong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.api.LoadTypeConfig;
import com.jiyun.frame.bean.BaseInfo;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.BaseMvpActiviy;
import com.jiyun.zhulong.model.AccountModel;
import com.jiyun.zhulong.mypackage.MyTextWatcher;
import com.jiyun.zhulong.utils.CheckUserNameAndPwd;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterPhoneActivity extends BaseMvpActiviy {


    @BindView(R.id.back_image)
    ImageView backImage;
    @BindView(R.id.title_content)
    TextView titleContent;
    @BindView(R.id.clearAccount)
    ImageView clearAccount;
    @BindView(R.id.accountContent)
    EditText accountContent;
    @BindView(R.id.visibleImage)
    ImageView visibleImage;
    @BindView(R.id.accountSecret)
    EditText accountSecret;
    @BindView(R.id.next_page)
    TextView nextPage;
    private String mPhoneNum;

    @Override
    protected int setLayout() {
        return R.layout.activity_register_phone;
    }

    @Override
    protected ICommonModel setModel() {
        return new AccountModel();
    }

    @Override
    protected void initView() {
        accountContent.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                clearAccount.setVisibility(s.length() > 0 ? View.VISIBLE : View.INVISIBLE);
                nextPage.setSelected(s.length() > 0 && accountContent.getText().length() > 0);
            }
        });

        accountSecret.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                visibleImage.setVisibility(s.length() > 0 ? View.VISIBLE : View.INVISIBLE);
                nextPage.setSelected(s.length() > 0 && accountContent.getText().length() >0);
            }
        });
    }

    @Override
    protected void initData() {
        titleContent.setText("创建账号");
        mPhoneNum = getIntent().getStringExtra("phoneNum");
    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] objects) {
            switch (apiConfig){
                case ApiConfig.NET_CHECK_USERNAME:
                    BaseInfo baseInfo = (BaseInfo) objects[0];
                    if (baseInfo.isSuccess()){
                        mPresenter.getData(ApiConfig.COMPLETE_REGISTER_WITH_SUBJECT, LoadTypeConfig.NORMAL,accountContent.getText().toString(),accountSecret.getText().toString(),mPhoneNum);
                    } else if (baseInfo.errNo == 114){
                        showToast("该用户名不可用");
                    }else showToast(baseInfo.msg);
                    break;
                case ApiConfig.COMPLETE_REGISTER_WITH_SUBJECT:
                    BaseInfo base = (BaseInfo) objects[0];
                    if (base.errNo ==24 || base.errNo == 114 || base.isSuccess()){
                        showToast("注册成功");
                        startActivity(new Intent(this,LoginActivity.class).putExtra("fromType","register_to_login"));
                        finish();
                    }else showToast(base.msg);

                    break;
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_image, R.id.clearAccount, R.id.visibleImage, R.id.next_page})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                finish();
                break;
            case R.id.clearAccount:
                accountContent.setText("");
                break;
            case R.id.visibleImage:
                accountSecret.setTransformationMethod(visibleImage.isSelected() ? PasswordTransformationMethod.getInstance() : HideReturnsTransformationMethod.getInstance());
                visibleImage.setSelected(!visibleImage.isSelected());
                break;
            case R.id.next_page:
                if (CheckUserNameAndPwd.verificationUsername(this,accountContent.getText().toString(),accountSecret.getText().toString()))
                    mPresenter.getData(ApiConfig.NET_CHECK_USERNAME,LoadTypeConfig.NORMAL,accountContent.getText().toString());
                break;
        }
    }
}