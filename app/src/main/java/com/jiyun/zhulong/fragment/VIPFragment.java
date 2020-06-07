package com.jiyun.zhulong.fragment;


import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.api.LoadTypeConfig;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.utils.ParamHashMap;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.BaseMvpFragment;
import com.youth.banner.Banner;

import butterknife.BindView;

/**
 * ：      --
 * 创建于： 2020/5/31 03:21
 * 邮箱：1750827655@qq.com
 */
public class VIPFragment extends BaseMvpFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.ctl)
    CollapsingToolbarLayout ctl;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.rv_horiaontal)
    RecyclerView rvHoriaontal;
    @BindView(R.id.rv_bottom)
    RecyclerView rvBottom;
    @BindView(R.id.cl)
    CoordinatorLayout cl;



    @Override
    protected int setLayout() {
        return R.layout.fragment_vip;
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
        /*ParamHashMap map = new ParamHashMap().add("specialty_id", specialty_id).add("page", page);
        mPresenter.getData(ApiConfig.VIP_BOTTOM_DATA_INFO, LoadTypeConfig.NORMAL, map);*/
    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] object) {

    }
}
