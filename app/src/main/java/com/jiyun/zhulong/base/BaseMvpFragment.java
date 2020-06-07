package com.jiyun.zhulong.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.jiyun.frame.mvp.CommonPresenter;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.mvp.ICommonView;

import butterknife.ButterKnife;

/**
 *    ：      --
 * 创建于： 2020/5/31 02:22
 *    邮箱：1750827655@qq.com
 */
public abstract class BaseMvpFragment<M extends ICommonModel> extends BaseFragment implements ICommonView {
    private M mModel;
    public CommonPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(setLayout(), container, false);
        ButterKnife.bind(this, view);
        mModel = setModel();
        if (mModel != null)
            mPresenter = new CommonPresenter(this, mModel);
        initView(view);
        initData();
        initListener();
        return view;
    }

    protected abstract int setLayout();

    protected abstract M setModel();

    protected abstract void initView(View view);

    protected abstract void initData();

    public void initListener() {
    }

    protected abstract void onSuccess(int apiConfig, int loadTypeConfig, Object[] object);

    public void onFailed(int apiConfig, Throwable throwable) {

    }

    @Override
    public void netSuccess(int apiConfig, int loadTypeConfig, Object[] object) {
        onSuccess(apiConfig, loadTypeConfig, object);
    }

    @Override
    public void netFailed(int apiConfig, Throwable throwable) {
        showLog("错误：" + apiConfig + "，******错误信息：" + throwable != null && !TextUtils.isEmpty(throwable.getMessage()) ? throwable.getMessage() : "不明错误类型");
        onFailed(apiConfig, throwable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.clear();
    }
}
