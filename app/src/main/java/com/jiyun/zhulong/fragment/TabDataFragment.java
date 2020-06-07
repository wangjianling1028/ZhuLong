package com.jiyun.zhulong.fragment;



import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.tabdata.adapter.TabDataVpAdapter;
import com.jiyun.tabdata.fragment.DataSquadFragment;
import com.jiyun.tabdata.fragment.NewsEliteFragment;
import com.jiyun.tabdata.fragment.NewsFragmnet;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.BaseMvpFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *    ：      --
 * 创建于： 2020/5/31 03:21
 *    邮箱：1750827655@qq.com
 */
public class TabDataFragment extends BaseMvpFragment {
    @BindView(R.id.vp_data)
    ViewPager vpData;
    @BindView(R.id.tv_squad)
    TextView tvSquad;
    @BindView(R.id.tv_news)
    TextView tvNews;
    @BindView(R.id.ll_tabdata)
    LinearLayout llTabdata;

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
        //NewsFragmnet newsFragmnet = new NewsFragmnet();
        fragments.add(dataSquadFragment);
        fragments.add(newsEliteFragment);
        TabDataVpAdapter adapter = new TabDataVpAdapter(getChildFragmentManager(), fragments);
        vpData.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        super.initListener();
        vpData.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==1){

                }
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

    @OnClick({R.id.tv_squad, R.id.tv_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_squad:

                break;
            case R.id.tv_news:

                break;
        }
    }
}
