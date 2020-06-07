package com.jiyun.zhulong.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jiyun.frame.bean.SpecialtyBean;
import com.jiyun.frame.constants.ConstantKey;
import com.jiyun.frame.context.FrameApplication;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.activity.HomeActivity;
import com.jiyun.zhulong.activity.MyHomeActivity;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *    ：      --
 * 创建于： 2020/6/2 23:48
 *    邮箱：1750827655@qq.com
 */
public class ChildSpecialtyRvAdapter extends RecyclerView.Adapter<ChildSpecialtyRvAdapter.ViewHolder> {
    private Context context;
    private List<SpecialtyBean.ResultBean.DataBean> datas = new ArrayList<>();
    private SpecialtyRvAdapter specialtyRvAdapter;

    public ChildSpecialtyRvAdapter(Context context, SpecialtyRvAdapter specialtyRvAdapter) {
        this.context = context;
        this.specialtyRvAdapter = specialtyRvAdapter;
    }

    public void initData(List<SpecialtyBean.ResultBean.DataBean> data) {
        this.datas.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_specialty_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SpecialtyBean.ResultBean.DataBean dataBean = datas.get(position);
        holder.tv_child_specialty.setText(dataBean.getSpecialty_name());
        if (FrameApplication.getFrameApplication().getSelectedInfo() != null && !TextUtils.isEmpty(FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id()) && FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id().equals(datas.get(position).getSpecialty_id())) {
            dataBean.setSelected(true);
            holder.tv_child_specialty.setTextColor(ContextCompat.getColor(context, R.color.white));
            holder.tv_child_specialty.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_subject_selected));
            //将选中的条目保存到sp中，用于再次启动时如果已经选择过专业就直接跳转到首页
            SharedPrefrenceUtils.putObject(context, ConstantKey.IS_SELECTDE, dataBean);
        } else {
            dataBean.setSelected(false);
            holder.tv_child_specialty.setTextColor(ContextCompat.getColor(context, R.color.fontColor333));
            holder.tv_child_specialty.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_radius_white_bg));
        }
        holder.tv_child_specialty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameApplication.getFrameApplication().setSelectedInfo(dataBean);
                specialtyRvAdapter.notifyDataSetChanged();
                onItemClickListener.onItemClick(position);
//                context.startActivity(new Intent(context, MyHomeActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_child_specialty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_child_specialty = itemView.findViewById(R.id.tv_child_specialty);
        }
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener{
        void onItemClick(int position);
    }
}
