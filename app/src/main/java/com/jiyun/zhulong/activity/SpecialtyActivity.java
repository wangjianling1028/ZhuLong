package com.jiyun.zhulong.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jiyun.frame.api.ApiConfig;
import com.jiyun.frame.api.LoadTypeConfig;
import com.jiyun.frame.bean.SpecialtyBean;
import com.jiyun.frame.constants.ConstantKey;
import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.adapter.SpecialtyRvAdapter;
import com.jiyun.zhulong.base.BaseMvpActiviy;
import com.jiyun.zhulong.model.SpecialtyModel;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SpecialtyActivity extends BaseMvpActiviy {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_specialty)
    TextView tvSpecialty;
    @BindView(R.id.group_specialty)
    Group groupSpecialty;
    @BindView(R.id.rv_group_specialty)
    RecyclerView rv_group_specialty;
    private SpecialtyRvAdapter adapter;
    private List<SpecialtyBean.ResultBean> result=new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.activity_specialty;
    }

    @Override
    protected ICommonModel setModel() {
        return new SpecialtyModel();
    }

    @Override
    protected void initView() {
        groupSpecialty.setVisibility(View.VISIBLE);
        tvSpecialty.setText(R.string.specialty);
        rv_group_specialty.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SpecialtyRvAdapter(this);
        rv_group_specialty.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        //若果没有缓存就读取网络数据，如果有缓存就使用缓存的数据
        if (SharedPrefrenceUtils.getSerializableList(this, ConstantKey.SUBJECT_LIST)!=null){
            result.addAll(SharedPrefrenceUtils.getSerializableList(this,ConstantKey.SUBJECT_LIST));
            adapter.initData(result);
            adapter.notifyDataSetChanged();
        }else {
            mPresenter.getData(ApiConfig.SPECIALTY_URL, LoadTypeConfig.NORMAL);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] objects) {
        switch (apiConfig) {
            case ApiConfig.SPECIALTY_URL:
                if (objects != null && objects.length > 0) {
                    SpecialtyBean specialtyBean = (SpecialtyBean) objects[0];
                    if (specialtyBean.getResult() != null && specialtyBean.getResult().size() > 0) {
                        result .addAll(specialtyBean.getResult());
                        adapter.initData(result);
                        //将网络获取的数据保存到本地
                        SharedPrefrenceUtils.putSerializableList(this,ConstantKey.SUBJECT_LIST,result);
                    }
                }
                break;
        }
    }

    @Override
    public void initListener() {
        super.initListener();

        adapter.setOnItemClickListener(new SpecialtyRvAdapter.OnGroupItemClickListener() {
            @Override
            public void onItemClick(int position) {
              //  finish();
               startActivity(new Intent(SpecialtyActivity.this,MyHomeActivity.class));
            }
        });

        //点击图片跳转首页页面
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        //将状态保存下来，用来再次选择专业时将上次选择的显示出来
        SharedPrefrenceUtils.putObject(this,ConstantKey.SUBJECT_LIST,mApplication.getSelectedInfo());
    }
}