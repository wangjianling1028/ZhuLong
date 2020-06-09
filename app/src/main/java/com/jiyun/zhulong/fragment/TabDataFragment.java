package com.jiyun.zhulong.fragment;


import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.tabdata.adapter.TabDataVpAdapter;
import com.jiyun.tabdata.fragment.DataSquadFragment;
import com.jiyun.tabdata.fragment.NewsEliteFragment;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.adapter.MyFragmentAdapter;
import com.jiyun.zhulong.base.BaseMvpFragment;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

/**
 * ：      --
 * 创建于： 2020/5/31 03:21
 * 邮箱：1750827655@qq.com
 */
public class TabDataFragment extends BaseMvpFragment {
    @BindView(R.id.vp_data)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    SegmentTabLayout tabLayout;

    @Override
    protected int setLayout() {
        return R.layout.fragment_tab_data;
    }

    @Override
    protected ICommonModel setModel() {
        return null;
    }

    @Override
    protected void initView(View view) {

        ArrayList<Fragment> fragments = new ArrayList<>();
        DataSquadFragment dataSquadFragment = new DataSquadFragment();
        NewsEliteFragment newsEliteFragment = new NewsEliteFragment();
        fragments.add(dataSquadFragment);
        fragments.add(newsEliteFragment);

        //TabDataVpAdapter adapter = new TabDataVpAdapter(getChildFragmentManager(), fragments);
            String[] strings ={"资料小组","最新精华"};
        MyFragmentAdapter adapter = new MyFragmentAdapter(getChildFragmentManager(), fragments, Arrays.asList(strings));
        viewPager.setAdapter(adapter);
        tabLayout.setTabData(strings);

        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
               viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] object) {

    }
}
