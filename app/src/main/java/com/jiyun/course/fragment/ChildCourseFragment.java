package com.jiyun.course.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.jiyun.bean.CourseDrillBean;
import com.jiyun.course.adapter.ChildCourseRvAdapter;
import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.api.LoadTypeConfig;
import com.jiyun.frame.bean.SpecialtyBean;
import com.jiyun.frame.constants.ConstantKey;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.utils.ParamHashMap;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.activity.MyHomeActivity;
import com.jiyun.zhulong.base.BaseMvpFragment;
import com.jiyun.zhulong.interfaces.DataListener;
import com.jiyun.zhulong.interfaces.OnRecyclerItemClick;
import com.jiyun.zhulong.loading.LoadView;
import com.jiyun.zhulong.model.CourseModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;


public class ChildCourseFragment extends BaseMvpFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    private String specialty_id;
    private ParamHashMap map;
    private int course_type;
    private ChildCourseRvAdapter adapter;
    private SpecialtyBean.ResultBean.DataBean dataBean;
    private List<CourseDrillBean.ResultBean.ListsBean> lists;

    //因为该fragment为3个页面公用而course_type参数为不确定的，可以通过构造方法将可变参数传过来
    public ChildCourseFragment(int course_type) {
        this.course_type=course_type;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_drill;
    }

    @Override
    protected ICommonModel setModel() {
        return new CourseModel();
    }

    @Override
    protected void initView(View view) {
        if (SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.IS_SELECTDE) != null) {
            dataBean = SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.IS_SELECTDE);
            specialty_id = dataBean.getSpecialty_id();
        }
        initRecyclerView(recyclerView);
        adapter = new ChildCourseRvAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        adapter.setmOnRecyclerItemClick(new OnRecyclerItemClick() {
            @Override
            public void onItemClick(int pos, Object[] pTS) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("itemInfo",lists.get(pos));
                bundle.putString("record","0");
                bundle.putString("f","1004");
                MyHomeActivity myHomeActivity= (MyHomeActivity) getActivity();
                myHomeActivity.navController.navigate(R.id.home_to_course_detail,bundle);
            }
        });
    }

    //由于涉及到选择专业的业务，当选择了专业需要及时更新数据所以在onResume生命周期方法中再次请求数据
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
                adapter.getLists().clear();
                initData();
            }
        }
    }

    //上拉加载，下拉刷新
    @Override
    public void initListener() {
        super.initListener();
        setSmartListener(refreshLayout, new DataListener() {
            @Override
            public void dataType(int loadTypeConfig) {
                if (loadTypeConfig == LoadTypeConfig.LOADMORE) {
                    page++;
                    initData();
                }
                if (loadTypeConfig == LoadTypeConfig.REFRESH) {
                    page = 1;
                    adapter.getLists().clear();
                    initData();
                }
            }
        });
    }

    @Override
    protected void initData() {
        LoadView.getInstance(getActivity(), null).show();
        map = new ParamHashMap().add("page", page).add("course_type", course_type).add("specialty_id", specialty_id);
        mPresenter.getData(ApiConfig.GET_COURSE_DATA, LoadTypeConfig.NORMAL, map);
    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] object) {
        LoadView.getInstance(getActivity(), null).dismiss();
        switch (apiConfig) {
            case ApiConfig.GET_COURSE_DATA:
                 CourseDrillBean courseDrillBeans = (CourseDrillBean) object[0];
                lists = courseDrillBeans.getResult().getLists();
                adapter.initData(lists);
                refreshLayout.finishLoadMore();
                refreshLayout.finishRefresh();
                break;
        }
    }
}
