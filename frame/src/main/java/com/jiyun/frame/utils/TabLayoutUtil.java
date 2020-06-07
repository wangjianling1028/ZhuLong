package com.jiyun.frame.utils;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 *    ：      --
 * 创建于： 2020/5/31 08:47
 *    邮箱：1750827655@qq.com
 */
public class TabLayoutUtil {

    public TabLayoutUtil() {
    }

    public static volatile TabLayoutUtil tabLayoutUtil;

    public static TabLayoutUtil getInstance() {
        if (tabLayoutUtil == null) {
            synchronized (TabLayoutUtil.class) {
                tabLayoutUtil = new TabLayoutUtil();
            }
        }
        return tabLayoutUtil;
    }

    //tablayout结合framelayout的二连动时使用
    public void TabAddFrameLayout(int containerID, FragmentManager manager, TabLayout tab, String[] tabTitles, Fragment[] fragments, int[] iconID) {
        FragmentTransaction begin = manager.beginTransaction();
        if (iconID == null && iconID.length == 0) {
            for (int i = 0; i < tabTitles.length; i++) {
                tab.addTab(tab.newTab().setText(tabTitles[i]));
                begin.add(containerID, fragments[i]).hide(fragments[i]);
            }
            begin.show(fragments[0]);
        } else {
            for (int i = 0; i < tabTitles.length; i++) {
                tab.addTab(tab.newTab().setText(tabTitles[i]).setIcon(iconID[i]));
                begin.add(containerID, fragments[i]).hide(fragments[i]);
            }
            begin.show(fragments[0]);
        }
        begin.commit();
    }

    //点击显示隐藏
    public void TabListener(TabLayout tab, FragmentManager manager, Fragment[] fragments) {
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction begin = manager.beginTransaction();
                for (int i = 0; i < fragments.length; i++) {
                    begin.hide(fragments[i]);
                }
                for (int i = 0; i < fragments.length; i++) {
                    if (tab.getPosition() == i) {
                        begin.show(fragments[i]);
                    }
                }
                begin.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }
}
