package com.jiyun.zhulong.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.activity.LoginActivity;
import com.jiyun.zhulong.base.BaseMvpFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ：      --
 * 创建于： 2020/5/31 03:22
 * 邮箱：1750827655@qq.com
 */
public class MineFragment extends BaseMvpFragment {
    @BindView(R.id.butt_login)
    Button buttLogin;

    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected ICommonModel setModel() {
        return null;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] object) {

    }

    @OnClick(R.id.butt_login)
    public void onClick() {

        startActivity(new Intent(getActivity(), LoginActivity.class));
    }
}
