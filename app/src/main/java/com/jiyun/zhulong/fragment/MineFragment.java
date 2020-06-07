package com.jiyun.zhulong.fragment;


import android.view.View;

import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.BaseMvpFragment;

/**
 *    ：      --
 * 创建于： 2020/5/31 03:22
 *    邮箱：1750827655@qq.com
 */
public class MineFragment extends BaseMvpFragment {
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
}
