package com.jiyun.zhulong.activity;


import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import com.jiyun.frame.bean.SpecialtyBean;
import com.jiyun.frame.constants.ConstantKey;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.frame.utils.TabLayoutUtil;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.BaseMvpActiviy;
import com.jiyun.zhulong.fragment.CourseFragment;
import com.jiyun.zhulong.fragment.MainHomeFragment;
import com.jiyun.zhulong.fragment.MineFragment;
import com.jiyun.zhulong.fragment.TabDataFragment;
import com.jiyun.zhulong.fragment.VIPFragment;
import com.jiyun.zhulong.mypackage.MayTabSelectedListener;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import butterknife.BindView;

public class HomeActivity extends BaseMvpActiviy {

    @BindView(R.id.tv_career)
    TextView tvCareer;
    @BindView(R.id.ll_career)
    LinearLayout llCareer;
    @BindView(R.id.rl_seek)
    RelativeLayout rlSeek;
    @BindView(R.id.img_home_msg)
    ImageView imgHomeMsg;
    @BindView(R.id.img_qrcode_scan)
    ImageView imgQrcodeScan;
    @BindView(R.id.ll_header)
    LinearLayout headerLl;
    @BindView(R.id.tab)
    TabLayout tab;
    private FragmentManager manager;
    private Fragment[] fragments;
    private SpecialtyBean.ResultBean.DataBean dataBean;

    @Override
    protected int setLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected ICommonModel setModel() {
        return null;
    }

    @Override
    protected void initView() {
        //获取手机存储的专业设置给textview
        if (SharedPrefrenceUtils.getObject(this, ConstantKey.IS_SELECTDE) != null) {
            dataBean = SharedPrefrenceUtils.getObject(this, ConstantKey.IS_SELECTDE);
            tvCareer.setText(dataBean.getSpecialty_name());
        }
        manager = getSupportFragmentManager();
        fragments = new Fragment[]{new MainHomeFragment(), new CourseFragment(), new VIPFragment(), new TabDataFragment(), new MineFragment()};
        String[] tabTitles = new String[]{"主页", "课程", "VIP", "资料", "我的"};
        int[] icons = new int[]{R.drawable.home_selector, R.drawable.course_selector, R.drawable.vip_selector, R.drawable.data_selector, R.drawable.mine_selector};
        TabLayoutUtil.getInstance().TabAddFrameLayout(R.id.fl, manager, tab, tabTitles, fragments, icons);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] objects) {

    }

    @Override
    public void initListener() {
        super.initListener();
        //tab点击
        tabListener();
        //头部点击
        headerListener();
    }

    private void headerListener() {
        //点击了专业
        llCareer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SpecialtyActivity.class);
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

    private void tabListener() {
        TabLayoutUtil.getInstance().TabListener(tab, manager, fragments);
        //由于业务需求需要有隐藏显示所以需要再次监听
        tab.addOnTabSelectedListener(new MayTabSelectedListener() {
            @Override
            public void onMayTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == fragments.length - 1) {
                    headerLl.setVisibility(View.GONE);
                } else {
                    headerLl.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    //连按两次返回键回退到桌面
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一下返回桌面",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                Intent i = new Intent(Intent.ACTION_MAIN);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addCategory(Intent.CATEGORY_HOME);
                startActivity(i);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
