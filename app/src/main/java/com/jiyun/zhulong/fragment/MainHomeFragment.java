package com.jiyun.zhulong.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.jiyun.frame.bean.SpecialtyBean;
import com.jiyun.frame.constants.ConstantKey;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.activity.SpecialtyActivity;
import com.jiyun.zhulong.base.BaseMvpFragment;
import com.jiyun.zhulong.design.BottomTabView;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 *    ：      --
 * 创建于： 2020/5/31 03:21
 *    邮箱：1750827655@qq.com
 */
public class MainHomeFragment extends BaseMvpFragment implements NavController.OnDestinationChangedListener, BottomTabView.OnTabClickListener {
    @BindView(R.id.bottom_tab)
    BottomTabView bottomTab;
    @BindView(R.id.ll_header)
    LinearLayout llHeader;
    @BindView(R.id.ll_career)
    LinearLayout llCareer;
    @BindView(R.id.rl_seek)
    RelativeLayout rlSeek;
    @BindView(R.id.img_home_msg)
    ImageView imgHomeMsg;
    @BindView(R.id.img_qrcode_scan)
    ImageView imgQrcodeScan;
    @BindView(R.id.tv_career)
    TextView tvCareer;
    private List<Integer> unSelectedIcon = new ArrayList<>();
    private List<Integer> selectedIcon = new ArrayList<>();
    private List<String> tabText = new ArrayList<>();
    protected NavController navController;
    private final int HOME = 1, COURSE = 2, VIP = 3, TAB_DATA = 4, MINE = 5;
    private int selected;
    private SpecialtyBean.ResultBean.DataBean dataBean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_main_home;
    }

    @Override
    protected ICommonModel setModel() {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.IS_SELECTDE) != null) {
            SpecialtyBean.ResultBean.DataBean dataBean = SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.IS_SELECTDE);
            if (this.dataBean.getSpecialty_name().equals(dataBean.getSpecialty_name())) {
                return;
            } else {
                tvCareer.setText(dataBean.getSpecialty_name());
            }
        }
    }

    @Override
    protected void initView(View view) {
        //获取手机存储的专业设置给textview
        if (SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.IS_SELECTDE) != null) {
            dataBean = SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.IS_SELECTDE);
            tvCareer.setText(dataBean.getSpecialty_name());
        }

        //定义未选中的图片
        Collections.addAll(unSelectedIcon, R.mipmap.ic_home_unselect_new, R.mipmap.ic_course_unselect_new, R.mipmap.vip11, R.mipmap.tab_data_normal, R.mipmap.ic_mine_unselect_new);
        //定义选中后要显示的图片
        Collections.addAll(selectedIcon, R.mipmap.ic_home_select_new, R.mipmap.ic_course_select_new, R.mipmap.vip, R.mipmap.tab_data_select, R.mipmap.ic_mine_select_new);
        //定义下方tab的内容
        Collections.addAll(tabText, "主页", "课程", "VIP", "资料", "我的");
        //将定义的资源传递给自定义的布局，并告诉它要显示几个
        bottomTab.setTabContent(tabText, unSelectedIcon, selectedIcon, 5);
        //下方tab的点击监听
        bottomTab.setOnTabClickListener(this);
        //获取到中间显示的试图区
        navController = Navigation.findNavController(view.findViewById(R.id.navigation_home_fragment));
        navController.addOnDestinationChangedListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] object) {

    }

    @Override
    public void initListener() {
        super.initListener();
        //点击了专业
        llCareer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SpecialtyActivity.class);
                startActivity(intent);
            }
        });
        //点击了搜索
        rlSeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //点击了信息
        imgHomeMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //点击了扫描二维码
        imgQrcodeScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        selectedIcon.clear();
        unSelectedIcon.clear();
        tabText.clear();
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

    }

    //下方tab点击事件
    @Override
    public void onTabClick(int tabId) {
        //如果当前所点击的是已经选中的就return掉，不让他再次加载布局
        if (tabId == selected) {
            return;
        }
        switch (tabId) {
            case HOME:
                navController.navigate(R.id.homeFragment);
                llHeader.setVisibility(View.VISIBLE);
                selected = HOME;
                break;
            case COURSE:
                navController.navigate(R.id.courseFragment);
                llHeader.setVisibility(View.VISIBLE);
                selected = COURSE;
                break;
            case VIP:
                navController.navigate(R.id.vipFragment);
                llHeader.setVisibility(View.VISIBLE);
                selected = VIP;
                break;
            case TAB_DATA:
                navController.navigate(R.id.tabDataFragment);
                llHeader.setVisibility(View.VISIBLE);
                selected = TAB_DATA;
                break;
            case MINE:
                navController.navigate(R.id.mineFragment);
                llHeader.setVisibility(View.GONE);
                selected = MINE;
                break;
        }
    }
}
