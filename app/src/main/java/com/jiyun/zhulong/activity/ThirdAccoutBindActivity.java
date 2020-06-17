package com.jiyun.zhulong.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.api.LoadTypeConfig;
import com.jiyun.frame.constants.ConstantKey;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.BaseMvpActiviy;
import com.jiyun.zhulong.model.AccountModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThirdAccoutBindActivity extends BaseMvpActiviy {


    @BindView(R.id.title_content)
    TextView titleContent;
    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.password)
    EditText password;
    private Parcelable mThirdData;

    @Override
    protected int setLayout() {
        return R.layout.activity_third_accout_bind;
    }

    @Override
    protected ICommonModel setModel() {
        return new AccountModel();
    }

    @Override
    protected void initView() {
        mThirdData = getIntent().getParcelableExtra("thirdData");
    }

    @Override
    protected void initData() {
        titleContent.setText("绑定账号");
    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] objects) {
        switch (apiConfig){
            case ApiConfig.BIND_ACCOUNT:
                setResult(ConstantKey.BIND_BACK_LOGIN);
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_image, R.id.button_bind})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                finish();
                break;
            case R.id.button_bind:
                if (TextUtils.isEmpty(account.getText().toString())){
                    showToast("用户名不能为空");
                }
                if (TextUtils.isEmpty(password.getText().toString())){
                    showToast("密码不能为空");
                }
                mPresenter.getData(ApiConfig.BIND_ACCOUNT, LoadTypeConfig.NORMAL,account.getText().toString(),password.getText().toString(),mThirdData);
                break;
        }
    }
}