package com.jiyun.zhulong.fragment;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jiyun.bean.VIPBannerBean;
import com.jiyun.bean.VIPBottomDataBean;
import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.api.LoadTypeConfig;
import com.jiyun.frame.bean.SpecialtyBean;
import com.jiyun.frame.constants.ConstantKey;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.utils.ParamHashMap;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.adapter.VIPAdapter;
import com.jiyun.zhulong.base.BaseMvpFragment;
import com.jiyun.zhulong.interfaces.DataListener;
import com.jiyun.zhulong.loading.LoadView;
import com.jiyun.zhulong.model.VipModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class VIPFragment extends BaseMvpFragment {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private SpecialtyBean.ResultBean.DataBean dataBean;
    private List<String> imgs = new ArrayList<>();
    private String specialty_id;
    private int page = 1;
    private VIPAdapter adapter;
    private ParamHashMap map;

    @Override
    protected int setLayout() {
        return R.layout.fragment_vip;
    }

    @Override
    protected ICommonModel setModel() {
        return new VipModel();
    }

    @Override
    protected void initView(View view) {
        if (SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.IS_SELECTDE) != null) {
            dataBean = SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.IS_SELECTDE);
            specialty_id = dataBean.getSpecialty_id();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new VIPAdapter(getActivity());
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        initBanner();
        initBotton();
    }

    private void initBotton() {
        map = new ParamHashMap().add("specialty_id", specialty_id).add("page", page);
        mPresenter.getData(ApiConfig.VIP_BOTTOM_DATA_INFO,LoadTypeConfig.NORMAL, map);

    }

    private void initBanner() {
        LoadView.getInstance(getActivity(), null).show();
      mPresenter.getData(ApiConfig.VIP_BANNER_DATA_INFO, LoadTypeConfig.NORMAL);
    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] object) {
        LoadView.getInstance(getActivity(), null).dismiss();
        switch (apiConfig){
            case ApiConfig.VIP_BANNER_DATA_INFO:
                if (((VIPBannerBean)object[0]).getResult() !=null){
                    VIPBannerBean vipBannerBean = (VIPBannerBean) object[0];
                    VIPBannerBean.ResultBean resultBean = vipBannerBean.getResult();
                    List<VIPBannerBean.ResultBean.LiveBeanX.LiveBean> live = resultBean.getLive().getLive();
                    adapter.initLive(live);

                    List<VIPBannerBean.ResultBean.LunbotuBean> lunbotu = resultBean.getLunbotu();
                    for (VIPBannerBean.ResultBean.LunbotuBean lunbotuBean:lunbotu) {
                      imgs.add(lunbotuBean.getImg());
                    }
                    adapter.initBanner(imgs);
                }
                break;
            case ApiConfig.VIP_BOTTOM_DATA_INFO:
                if (((VIPBottomDataBean)object[0]).getResult() != null){
                    if (loadTypeConfig == LoadTypeConfig.LOADMORE){
                        refreshLayout.finishLoadMore();
                    }else if (loadTypeConfig == LoadTypeConfig.REFRESH){
                        if (adapter.getList().size() >0){
                            adapter.getList().clear();
                        }
                        refreshLayout.finishRefresh();
                    }
                    VIPBottomDataBean vipBottomDataBean = (VIPBottomDataBean) object[0];
                    List<VIPBottomDataBean.ResultBean.ListBean> list=vipBottomDataBean.getResult().getList();
                    adapter.initList(list);
                }

                break;
        }
    }
    @Override
    public void initListener() {
        super.initListener();
        setSmartListener(refreshLayout, new DataListener() {
            @Override
            public void dataType(int loadTypeConfig) {
                if (loadTypeConfig == LoadTypeConfig.LOADMORE) {
                    page++;
                    map = new ParamHashMap().add("specialty_id", specialty_id).add("page", page);
                    mPresenter.getData(ApiConfig.VIP_BOTTOM_DATA_INFO, LoadTypeConfig.LOADMORE, map);
                }
                if (loadTypeConfig == LoadTypeConfig.REFRESH) {
                    page = 1;
                    map = new ParamHashMap().add("specialty_id", specialty_id).add("page", page);
                    mPresenter.getData(ApiConfig.VIP_BOTTOM_DATA_INFO, LoadTypeConfig.REFRESH, map);
                }
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        if (SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.IS_SELECTDE) != null) {
            SpecialtyBean.ResultBean.DataBean dataBean = SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.IS_SELECTDE);
            if (this.dataBean.getSpecialty_id().equals(dataBean.getSpecialty_id())) {
                return;
            } else {
                specialty_id = dataBean.getSpecialty_id();
                page = 1;
                adapter.getList().clear();
                map = new ParamHashMap().add("specialty_id", specialty_id).add("page", page);
                mPresenter.getData(ApiConfig.VIP_BOTTOM_DATA_INFO, LoadTypeConfig.NORMAL, map);
            }
        }
    }
}
