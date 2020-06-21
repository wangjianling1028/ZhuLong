package com.jiyun.zhulong.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jiyun.frame.api.LoadTypeConfig;
import com.jiyun.zhulong.activity.HomeActivity;
import com.jiyun.zhulong.interfaces.DataListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

/**
 *    ：      --
 * 创建于： 2020/5/31 03:06
 *    邮箱：1750827655@qq.com
 */
public class BaseFragment extends Fragment {



    public void setSmartListener(SmartRefreshLayout smartRefreshLayout, DataListener dataListener){
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                dataListener.dataType(LoadTypeConfig.LOADMORE);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                dataListener.dataType(LoadTypeConfig.REFRESH);
            }
        });
    }

    public void initRecyclerView(RecyclerView recyclerView){
        if (recyclerView!=null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        }
    }

    public void showLog(String str){
        Log.e("error：", str );
    }

    public void showToast(String str){
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    public int setColor(@ColorRes int pColor){
        return ContextCompat.getColor(getContext(),pColor);
    }

    public HomeActivity getHomeActivity(){
        return (HomeActivity)getActivity();
    }
}
