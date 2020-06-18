package com.jiyun.tabdata.fragment;

import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jiyun.bean.NewsEliteBean;
import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.api.LoadTypeConfig;
import com.jiyun.frame.bean.SpecialtyBean;
import com.jiyun.frame.constants.ConstantKey;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.utils.ParamHashMap;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.adapter.NewsEliteAdapter;
import com.jiyun.zhulong.base.BaseMvpFragment;
import com.jiyun.zhulong.interfaces.DataListener;
import com.jiyun.zhulong.loading.LoadView;
import com.jiyun.zhulong.model.NewsEliteMolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class NewsEliteFragment extends BaseMvpFragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyNewsElite;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int page = 1;
    private ArrayList<NewsEliteBean.ResultBean> resultBeans;
    private NewsEliteAdapter adapter;
    private SpecialtyBean.ResultBean.DataBean dataBean;
    private int fid;

    @Override
    protected int setLayout() {
        return R.layout.refresh_list_layout;
    }

    @Override
    protected ICommonModel setModel() {
        return new NewsEliteMolder();
    }

    @Override
    protected void initView(View view) {

        if (SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.IS_SELECTDE) != null) {
            dataBean = SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.IS_SELECTDE);
            fid = dataBean.getFid();
        }

        mRecyNewsElite.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyNewsElite.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        resultBeans = new ArrayList<>();
        adapter = new NewsEliteAdapter(getActivity(), resultBeans);
        mRecyNewsElite.setAdapter(adapter);

    }

    @Override
    public void initListener() {
        super.initListener();
        setSmartListener(refreshLayout, new DataListener() {
            @Override
            public void dataType(int loadTypeConfig) {
                if (loadTypeConfig == LoadTypeConfig.LOADMORE) {
                    page++;
                    ParamHashMap map = new ParamHashMap().add("page", page).add("fid", fid);
                    mPresenter.getData(ApiConfig.GET_NEWS_DATA, LoadTypeConfig.NORMAL, map);
                }
                if (loadTypeConfig == LoadTypeConfig.REFRESH) {
                    page = 1;
                    resultBeans.clear();
                    ParamHashMap map = new ParamHashMap().add("page", page).add("fid", fid);
                    mPresenter.getData(ApiConfig.GET_NEWS_DATA, LoadTypeConfig.NORMAL, map);
                }
            }
        });
    }

    @Override
    protected void initData() {
        LoadView.getInstance(getActivity(), null).show();
        ParamHashMap map = new ParamHashMap().add("page", page).add("fid", fid);
        mPresenter.getData(ApiConfig.GET_NEWS_DATA, LoadTypeConfig.NORMAL, map);

    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] object) {
        LoadView.getInstance(getActivity(), null).dismiss();
        switch (apiConfig) {
            case ApiConfig.GET_NEWS_DATA:
                NewsEliteBean newsEliteBean = (NewsEliteBean) object[0];
                List<NewsEliteBean.ResultBean> result = newsEliteBean.getResult();
                resultBeans.addAll(result);
                adapter.notifyDataSetChanged();
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                break;
        }
    }
}
