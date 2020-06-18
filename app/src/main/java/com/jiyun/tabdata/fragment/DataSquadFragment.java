package com.jiyun.tabdata.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jiyun.bean.DataSquadBean;
import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.api.LoadTypeConfig;
import com.jiyun.frame.bean.BaseInfo;
import com.jiyun.frame.bean.SpecialtyBean;
import com.jiyun.frame.constants.ConstantKey;
import com.jiyun.frame.context.FrameApplication;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.utils.ParamHashMap;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.activity.HomeActivity;
import com.jiyun.zhulong.activity.LoginActivity;
import com.jiyun.zhulong.activity.MyHomeActivity;
import com.jiyun.zhulong.adapter.DataSquadRVAdapter;
import com.jiyun.zhulong.base.BaseMvpFragment;
import com.jiyun.zhulong.interfaces.DataListener;

import com.jiyun.zhulong.interfaces.OnRecyclerItemClick;
import com.jiyun.zhulong.loading.LoadView;
import com.jiyun.zhulong.model.DataSquadModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


import static com.jiyun.zhulong.adapter.DataSquadRVAdapter.FOCUS_TYPE;
import static com.jiyun.zhulong.adapter.DataSquadRVAdapter.ITEM_TYPE;


public class DataSquadFragment extends BaseMvpFragment  {

    @BindView(R.id.recyclerView)
    RecyclerView datasquadRecy;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mSmartSquad;
    private int page = 1;
    private ArrayList<DataSquadBean.ResultBean> resultBeans;
    private DataSquadRVAdapter adapter;
    private ParamHashMap map;

    private SpecialtyBean.ResultBean.DataBean dataBean;
    private int fid;

    @Override
    protected int setLayout() {
        return R.layout.refresh_list_layout;
    }

    @Override
    protected ICommonModel setModel() {
        return new DataSquadModel();
    }

    @Override
    protected void initView(View view) {
        if (SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.IS_SELECTDE) != null) {
            dataBean = SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.IS_SELECTDE);
            fid = dataBean.getFid();
        }

        datasquadRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        resultBeans = new ArrayList<>();
        adapter = new DataSquadRVAdapter(getActivity(), resultBeans);
        datasquadRecy.setAdapter(adapter);

        adapter.setmOnRecyclerItemClick(new OnRecyclerItemClick() {
            @Override
            public void onItemClick(int pos, Object[] pTS) {
                DataSquadBean.ResultBean resultBean = resultBeans.get(pos);
                switch ((int)pTS[0]){
                    case ITEM_TYPE:
                        MyHomeActivity activity = (MyHomeActivity) getActivity();
                        Bundle bundle = new Bundle();
                        bundle.putString(ConstantKey.GROU_TO_DETAIL_GID,resultBeans.get(pos).getGid());
                        bundle.putString(ConstantKey.GROU_TO_DETAIL_NAME,resultBeans.get(pos).getGroup_name());
                        activity.navController.navigate(R.id.dataGroupDetailFragment,bundle);
                        break;


                    case FOCUS_TYPE:
                        DataSquadBean.ResultBean entiy = resultBeans.get(pos);
                        boolean login = FrameApplication.getFrameApplication().isLogin();
                        if (login){
                            if (entiy.isFocus()){ // 已关注  取消关注
                                ParamHashMap cancelMap = new ParamHashMap().add("group_id", resultBean.getGid()).add("type", 1).add("screctKey", FrameApplication.getFrameApplicationContext().getString(R.string.secrectKey_posting));
                                mPresenter.getData(ApiConfig.CLICK_CANCEL_FOCUS, LoadTypeConfig.NORMAL, cancelMap,pos);
                            }else{  // 没有关注 点击关注
                                ParamHashMap addMap = new ParamHashMap().add("gid", resultBean.getGid()).add("group_name", resultBean.getGroup_name()).add("screctKey", FrameApplication.getFrameApplicationContext().getString(R.string.secrectKey_posting));
                                mPresenter.getData(ApiConfig.CLICK_TO_FOCUS, LoadTypeConfig.NORMAL, addMap,pos);
                            }
                        }else{
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                        }
                        break;
                }
            }
        });
    }



    @Override
    protected void initData() {
        LoadView.getInstance(getActivity(), null).show();

        map = new ParamHashMap().add("page", page).add("type", 1).add("fid", fid);
        mPresenter.getData(ApiConfig.GET_SQUAD_DATA, LoadTypeConfig.NORMAL, map);
    }


    @Override
    protected void onSuccess(int apiConfig,int loadTypeConfig, Object[] object) {
        LoadView.getInstance(getActivity(), null).dismiss();
        switch (apiConfig) {
            case ApiConfig.GET_SQUAD_DATA:
                DataSquadBean dataSquadBean = (DataSquadBean) object[0];
                List<DataSquadBean.ResultBean> result = dataSquadBean.getResult();
                resultBeans.addAll(result);
                adapter.notifyDataSetChanged();
                mSmartSquad.finishLoadMore();
                mSmartSquad.finishRefresh();
                break;
            case ApiConfig.CLICK_CANCEL_FOCUS:
                BaseInfo base = (BaseInfo) object[0];
                int clickPos= (int) object[1];
                if (base.isSuccess()){
                    showToast("取消成功");
                    resultBeans.get(clickPos).setIs_ftop(0);
                    adapter.notifyDataSetChanged();
                }
                break;
            case ApiConfig.CLICK_TO_FOCUS:
                BaseInfo baseSuc = (BaseInfo) object[0];
                int clickJoinPos = (int) object[1];
                if (baseSuc.isSuccess()){
                    showToast("关注成功");
                    resultBeans.get(clickJoinPos).setIs_ftop(1);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void initListener() {
        super.initListener();
        setSmartListener(mSmartSquad, new DataListener() {
            @Override
            public void dataType(int loadTypeConfig) {
                if (loadTypeConfig == LoadTypeConfig.LOADMORE) {
                       page++;
                     map = new ParamHashMap().add("page", page).add("type", 1).add("fid", fid);
                     mPresenter.getData(ApiConfig.GET_SQUAD_DATA, LoadTypeConfig.LOADMORE, map);
                }
                if (loadTypeConfig == LoadTypeConfig.REFRESH) {
                    page = 1;
                    resultBeans.clear();
                    map = new ParamHashMap().add("page", page).add("type", 1).add("fid", fid);
                    mPresenter.getData(ApiConfig.GET_SQUAD_DATA, LoadTypeConfig.REFRESH, map);
                }
            }
        });
    }
}
