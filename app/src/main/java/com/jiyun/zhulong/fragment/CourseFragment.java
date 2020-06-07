package com.jiyun.zhulong.fragment;


import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jiyun.course.adapter.CourseVpAdapter;
import com.jiyun.course.fragment.ChildCourseFragment;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.tabdata.adapter.TabDataVpAdapter;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.BaseMvpFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 *    ：      --
 * 创建于： 2020/5/31 03:21
 *    邮箱：1750827655@qq.com
 */
public class CourseFragment extends BaseMvpFragment {
    @BindView(R.id.tab_course)
    TabLayout tabCourse;
    @BindView(R.id.vp_course)
    ViewPager vpCourse;

    @Override
    protected int setLayout() {
        return R.layout.fragment_course;
    }

    @Override
    protected ICommonModel setModel() {
        return null;
    }

    @Override
    protected void initView(View view) {
        ArrayList<String> tabTitles = new ArrayList<>();
        tabTitles.add("训练营");
        tabTitles.add("精品课");
        tabTitles.add("公开课");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ChildCourseFragment(3));
        fragments.add(new ChildCourseFragment(1));
        fragments.add(new ChildCourseFragment(2));
        tabCourse.setupWithViewPager(vpCourse);
        CourseVpAdapter adapter = new CourseVpAdapter(getChildFragmentManager(),tabTitles,fragments);
        vpCourse.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] object) {

    }
}
