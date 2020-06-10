package com.jiyun.tabdata.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 *    ：      --
 * 创建于： 2020/6/6 15:23
 *    邮箱：1750827655@qq.com
 */
public class TabDataVpAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragments;

    public TabDataVpAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
