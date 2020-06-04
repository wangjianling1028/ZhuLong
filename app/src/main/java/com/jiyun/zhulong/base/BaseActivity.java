package com.jiyun.zhulong.base;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jiyun.frame.api.LoadTypeConfig;
import com.jiyun.zhulong.interfaces.DataListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class BaseActivity extends AppCompatActivity {
    public Application1907 mApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (Application1907) getApplication();
    }


    public void initSmartListener(SmartRefreshLayout refreshLayout, DataListener dataListener){
        if (refreshLayout !=null&& dataListener !=null){
            refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
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
    }

    public void initRecyclerView(RecyclerView recyclerView) {
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }
    }

    public void showLog(String str) {
        Log.e("error：", str);
    }

    public void showToast(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

}
