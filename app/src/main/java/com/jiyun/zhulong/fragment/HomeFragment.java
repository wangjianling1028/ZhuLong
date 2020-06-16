package com.jiyun.zhulong.fragment;


import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jiyun.bean.BannerLiveInfo;
import com.jiyun.bean.IndexCommondEntity;
import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.api.LoadTypeConfig;
import com.jiyun.frame.bean.BaseInfo;
import com.jiyun.frame.bean.SpecialtyBean;
import com.jiyun.frame.constants.ConstantKey;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.utils.ParamHashMap;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.adapter.MainHomeAdapter;
import com.jiyun.zhulong.base.BaseMvpFragment;
import com.jiyun.zhulong.interfaces.DataListener;
import com.jiyun.zhulong.model.MainPageModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * ：      --
 * 创建于： 2020/6/5 11:43
 * 邮箱：1750827655@qq.com
 */
public class HomeFragment extends BaseMvpFragment  {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mSmartLayout;

    private int currentPage = 1;
    private List<IndexCommondEntity> bottomList = new ArrayList<>();
    private List<String> bannerData = new ArrayList<>();
    private List<BannerLiveInfo.Live> liveData = new ArrayList<>();
    private ParamHashMap add;
    private ParamHashMap map;
    private MainHomeAdapter mAdapter;
    private SpecialtyBean.ResultBean.DataBean dataBean;
    private String specialty_id;

    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected ICommonModel setModel() {
        return new MainPageModel();
    }

    @Override
    protected void initView(View view) {

        if (SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.IS_SELECTDE) != null) {
            dataBean = SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.IS_SELECTDE);
            specialty_id = dataBean.getSpecialty_id();
        }


        initRecyclerView(mRecyclerView);
        mAdapter = new MainHomeAdapter(bottomList, bannerData, liveData, getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {

        map = new ParamHashMap().add("specialty_id", specialty_id).add("page", currentPage).add("limit", 10);
        mPresenter.getData(ApiConfig.GET_HoME1, LoadTypeConfig.NORMAL, map);

        add = new ParamHashMap().add("pro", specialty_id).add("more_live", 1).add("is_new", 1).add("new_banner", 1);
        mPresenter.getData(ApiConfig.GET_HoME2, LoadTypeConfig.NORMAL,add);
    }


    private boolean mainList = false, banLive = false;

    @Override
    protected void onSuccess(int apiConfig, int loadMode, Object[] object) {
        switch (apiConfig) {
            case ApiConfig.GET_HoME1:
                BaseInfo<List<IndexCommondEntity>> baseInfo = (BaseInfo<List<IndexCommondEntity>>) object[0];
                if (baseInfo.isSuccess()) {
                    if (loadMode == LoadTypeConfig.LOADMORE) mSmartLayout.finishLoadMore();
                    if (loadMode == LoadTypeConfig.REFRESH) {
                        bottomList.clear();
                        mSmartLayout.finishRefresh();
                    }
                    bottomList.addAll(baseInfo.result);
                    mainList = true;
                    if (banLive) {
                        mAdapter.notifyDataSetChanged();
                        mainList = false;
                    }
                } else showToast("列表加载错误");
                break;
            case ApiConfig.GET_HoME2:
                JsonObject jsonObject = (JsonObject) object[0];
                try {
                    JSONObject object1 = new JSONObject(jsonObject.toString());
                    if (object1.getString("errNo").equals("0")) {
                        if (loadMode == LoadTypeConfig.REFRESH) {
                            bannerData.clear(); liveData.clear();
                        }
                        String result = object1.getString("result");
                        JSONObject resultObject = new JSONObject(result);
                        String live = resultObject.getString("live");

                        if (live.equals("true") || live.equals("false")) {
                            resultObject.remove("live");
                        }
                        result = resultObject.toString();
                        Gson gson = new Gson();
                        BannerLiveInfo info = gson.fromJson(result, BannerLiveInfo.class);
                        for (BannerLiveInfo.Carousel data : info.Carousel) {
                            bannerData.add(data.thumb);
                        }
                        liveData.addAll(info.live);
                        banLive = true;
                        if (mainList) {
                            mAdapter.notifyDataSetChanged();
                            banLive = false;
                        }
                    }
                } catch (JSONException pE) {
                    pE.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void initListener() {
        super.initListener();
        setSmartListener(mSmartLayout, new DataListener() {
            @Override
            public void dataType(int loadTypeConfig) {
                if (loadTypeConfig == LoadTypeConfig.LOADMORE) {
                    currentPage++;
                    map = new ParamHashMap().add("specialty_id", specialty_id).add("page", currentPage).add("limit", 10);
                    mPresenter.getData(ApiConfig.GET_HoME1, LoadTypeConfig.NORMAL, map);
                }
                if (loadTypeConfig == LoadTypeConfig.REFRESH) {
                    currentPage = 1;
                    map = new ParamHashMap().add("specialty_id", specialty_id).add("page", currentPage).add("limit", 10);
                    mPresenter.getData(ApiConfig.GET_HoME1, LoadTypeConfig.NORMAL, map);
                }
            }
        });
    }
}
