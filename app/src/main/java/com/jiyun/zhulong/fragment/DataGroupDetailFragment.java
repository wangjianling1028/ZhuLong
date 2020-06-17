package com.jiyun.zhulong.fragment;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiyun.bean.GroupDetailEntity;
import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.api.LoadTypeConfig;
import com.jiyun.frame.bean.BaseInfo;
import com.jiyun.frame.constants.ConstantKey;
import com.jiyun.frame.constants.Constants;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.utils.ParamHashMap;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.activity.MyHomeActivity;
import com.jiyun.zhulong.adapter.DataGroupDetailBottomAdapter;
import com.jiyun.zhulong.adapter.GroupDetailCenterTabAdapter;
import com.jiyun.zhulong.adapter.GroupDetailPopAdapter;
import com.jiyun.zhulong.base.BaseMvpFragment;
import com.jiyun.zhulong.design.LoginView;
import com.jiyun.zhulong.interfaces.DataListener;
import com.jiyun.zhulong.interfaces.OnRecyclerItemClick;
import com.jiyun.zhulong.loading.LoadView;
import com.jiyun.zhulong.model.DataSquadModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yiyatech.utils.newAdd.GlideUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import razerdp.basepopup.BasePopupWindow;
import razerdp.design.GroupTabPopup;


public class DataGroupDetailFragment extends BaseMvpFragment {


    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.iv_thumb)
    ImageView ivThumb;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_member_num)
    TextView tvMemberNum;
    @BindView(R.id.tv_post_num)
    TextView tvPostNum;
    @BindView(R.id.tv_focus)
    TextView tvFocus;
    @BindView(R.id.groupBack)
    ImageView groupBack;
    @BindView(R.id.groupTitle)
    TextView groupTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabRecycle)
    RecyclerView tabRecycle;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String mGid;
    private MyHomeActivity mActivity;
    private GroupDetailPopAdapter mPopAdapter;

    private List<GroupDetailEntity.Tag> mTabListData = new ArrayList<>();
    private List<GroupDetailEntity.Thread> mBottomData = new ArrayList<>();
    private List<GroupDetailEntity.Tag.SelectsBean> mPopData = new ArrayList<>();
    private List<String> mContains = new ArrayList<>();
    private DataGroupDetailBottomAdapter mDataGroupDetailBottomAdapter;
    private GroupDetailCenterTabAdapter mGroupDetailCenterTabAdapter;
    private GroupTabPopup mGroupTabPopup;



    @Override
    protected int setLayout() {
        return R.layout.fragment_data_group_detail;
    }

    @Override
    protected ICommonModel setModel() {
        return new DataSquadModel();
    }

    @Override
    protected void initView(View view) {
        mActivity = (MyHomeActivity) getActivity();
        if (getArguments() != null) {
            mGid = getArguments().getString(ConstantKey.GROU_TO_DETAIL_GID);
        }
        groupTitle.setVisibility(View.GONE);
        appBar.addOnOffsetChangedListener((pAppBarLayout, verticalOffset) -> {
            boolean space = Math.abs(verticalOffset) >= tvName.getBottom();
            groupTitle.setVisibility(space ? View.VISIBLE : View.GONE);
            toolbar.setBackgroundColor(space ? setColor(R.color.app_theme) : Color.TRANSPARENT);
        });
        initRecyclerView(recyclerView);
        mDataGroupDetailBottomAdapter = new DataGroupDetailBottomAdapter(getContext(), mBottomData);
        recyclerView.setAdapter(mDataGroupDetailBottomAdapter);
        LinearLayoutManager ma = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        tabRecycle.setLayoutManager(ma);
        mGroupDetailCenterTabAdapter = new GroupDetailCenterTabAdapter(getContext(), mTabListData);
        tabRecycle.setAdapter(mGroupDetailCenterTabAdapter);

        mGroupDetailCenterTabAdapter.setmOnRecyclerItemClick(new OnRecyclerItemClick() {
            @Override
            public void onItemClick(int pos, Object[] pTS) {
                if (mTabListData.get(pos).getSelects() != null){
                    clickCenterTab(pos);
                }else{
                    showToast("该标签没有选择条件");
                }
            }
        });
    }

    private int currentTabPos = -1;

    private void clickCenterTab(int pos) {
        currentTabPos = pos;
        GroupDetailEntity.Tag tag = mTabListData.get(pos);
        tag.setSelecting(!tag.isSelecting());
        if (mPopData.size() != 0) mPopData.clear();
        if (mContains.size() != 0) mContains.clear();
        mPopData.addAll(tag.getSelects());
        mContains.addAll(tag.getContainsName());
        mGroupDetailCenterTabAdapter.notifyItemChanged(pos);
        if (mGroupTabPopup == null) {
            mGroupTabPopup = new GroupTabPopup(getActivity());
            mGroupTabPopup.popRecycle.setLayoutManager(new GridLayoutManager(getContext(), 2));
            mPopAdapter = new GroupDetailPopAdapter(getContext(), mPopData, mContains);
            mGroupTabPopup.popRecycle.setAdapter(mPopAdapter);
        }
        mPopAdapter.notifyDataSetChanged();
        mGroupTabPopup.showPopupWindow(tabRecycle);
        mGroupTabPopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tag.setSelecting(!tag.isSelecting());
                mGroupDetailCenterTabAdapter.notifyItemChanged(pos);
            }
        });
        mPopAdapter.setOnRecyclerItemClick((pos1, pObjects) -> {
            popTabClick(pos1);
        });
    }
    private int currentPopPos = -1;
    private void popTabClick(int pos) {
        currentPopPos = pos;
        GroupDetailEntity.Tag.SelectsBean selectsBean = mPopData.get(pos);
        tags = selectsBean.getUrl();
        getFooterData(LoadTypeConfig.REFRESH);
    }


    @Override
    protected void initData() {
      //  mPresenter.getData(ApiConfig.GROUP_DETAIL, LoadTypeConfig.NORMAL,mGid);

        LoadView.getInstance(getActivity(), null).show();
        mPresenter.getData(ApiConfig.GROUP_DETAIL,LoadTypeConfig.NORMAL, mGid);

    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] object) {
        LoadView.getInstance(getActivity(),null).dismiss();
        switch (apiConfig){
            case ApiConfig.GROUP_DETAIL:
                BaseInfo<GroupDetailEntity> baseInfo = (BaseInfo<GroupDetailEntity>) object[0];
                if (baseInfo.isSuccess()){
                    GroupDetailEntity groupDetailEntity=baseInfo.result;
                    setDetailData(groupDetailEntity);
                   // getFooterData(LoadTypeConfig.NORMAL);
                    mBottomData.addAll(baseInfo.result.getThread_list());
                    mDataGroupDetailBottomAdapter.notifyDataSetChanged();
                }
                break;
            case ApiConfig.GROUP_DETAIL_FOOTER_DATA:
               /* BaseInfo<GroupDetailEntity> base = (BaseInfo<GroupDetailEntity>) object[0];
                if (base.isSuccess()){
                     if (base.result.getThread_list().size() < Constants.LIMIT_NUM) refreshLayout.setNoMoreData(true);
                    mBottomData.addAll(base.result.getThread_list());
                    mDataGroupDetailBottomAdapter.notifyDataSetChanged();
                }*/

                String s = object[0].toString();
                try {
                    JSONObject bigJson = new JSONObject(s);
                    int errNo = bigJson.getInt("errNo");
                    if (errNo == 0) {
                        JSONObject result = bigJson.getJSONObject("result");
                        String thread_list = result.getString("thread_list");
                        Gson gson = new Gson();
                        List<GroupDetailEntity.Thread> list = gson.fromJson(thread_list, new TypeToken<List<GroupDetailEntity.Thread>>() {
                        }.getType());

                        if (loadTypeConfig == LoadTypeConfig.REFRESH) {
                            refreshLayout.finishRefresh();
                            mBottomData.clear();
                        } else if (loadTypeConfig == LoadTypeConfig.LOADMORE) {
                            refreshLayout.finishLoadMore();
                            if (list.size() < Constants.LIMIT_NUM)
                                refreshLayout.setNoMoreData(true);
                        }
                        mBottomData.addAll(list);
                        mDataGroupDetailBottomAdapter.notifyDataSetChanged();
                        //未进行tab点击时，刷新加载currentTabPos为-1，执行以下内容会角标越界
                        if (currentTabPos != -1) {
                            GroupDetailEntity.Tag tag = mTabListData.get(currentTabPos);
                            List<String> containsName = tag.getContainsName();
                            String name = tag.getSelects().get(currentPopPos).getName();
                            //有一个默认选中的，第一次加载一成功加入选中集合，为防止以后重复添加，这里在点击时（已经完成第一次加载），将其设为0；
                            if (tag.getOn() == 1) tag.setOn(0);
                            if (containsName.contains(name)) {
                                containsName.clear();
                            } else {
                                containsName.clear();
                                containsName.add(name);
                            }
                            tag.setContainsName(containsName);
                        }

                    }
                } catch (JSONException pE) {
                    pE.printStackTrace();
                }
                //当点击时创建，如果没有点击，直接刷新或加载更多，该对象为空
                if (mGroupTabPopup != null)mGroupTabPopup.dismiss();
                break;
        }
    }

    private int page = 1;
    private String tags = "";

    private void getFooterData(int normal) {
        ParamHashMap add = new ParamHashMap().add("gid", mGid).add("page", page).add("limit", Constants.LIMIT_NUM);
        if (!TextUtils.isEmpty(tags)) add.add("tagall",tags);
        mPresenter.getData(ApiConfig.GROUP_DETAIL_FOOTER_DATA,normal,add);

    }


    @Override
    public void initListener() {
        super.initListener();
        setSmartListener(refreshLayout, new DataListener() {
            @Override
            public void dataType(int loadTypeConfig) {
                if (loadTypeConfig == LoadTypeConfig.LOADMORE) {
                    page++;
                    getFooterData(LoadTypeConfig.LOADMORE);
                    refreshLayout.finishLoadMore();
                }
                if (loadTypeConfig == LoadTypeConfig.REFRESH) {
                    page = 1;
                    getFooterData(LoadTypeConfig.REFRESH);
                    refreshLayout.finishRefresh();
                }
            }
        });
    }


    private void setDetailData(GroupDetailEntity groupInfo) {
        GroupDetailEntity.Group groupInner = groupInfo.getGroupinfo();
        tvName.setText(groupInner.getGroup_name());
        groupTitle.setText(groupInner.getGroup_name());
        tvMemberNum.setText("成员 "+ groupInner.getMember_num() + " 人");
        tvPostNum.setText("资料 " + groupInner.getThread_num() + " 篇" );
        tvFocus.setText(groupInner.getIs_add() == 1 ? "已关注" : "关注");
        GlideUtil.loadCornerImage(ivThumb,groupInner.getLogo(),null,10);
        GlideUtil.loadBlurredBackground(groupInner.getLogo(),imageBack);
        mTabListData.addAll(groupInfo.getTag_arr());
        mGroupDetailCenterTabAdapter.notifyDataSetChanged();
    }

    @OnClick({ R.id.groupBack})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.groupBack:
              //  mActivity.navController.navigateUp();
                mActivity.navController.navigate(R.id.dataGroup_back_to_home);
                break;
        }
    }
}