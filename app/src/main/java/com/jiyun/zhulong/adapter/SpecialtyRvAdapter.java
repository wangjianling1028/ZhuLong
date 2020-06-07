package com.jiyun.zhulong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jiyun.frame.bean.SpecialtyBean;
import com.jiyun.zhulong.R;

import java.util.ArrayList;
import java.util.List;

/**
 *    ：      --
 * 创建于： 2020/6/2 21:10
 *    邮箱：1750827655@qq.com
 */
public class SpecialtyRvAdapter extends RecyclerView.Adapter<SpecialtyRvAdapter.ViewHolder> {
    private Context context;
    private List<SpecialtyBean.ResultBean> result = new ArrayList<>();
    private ChildSpecialtyRvAdapter adapter;

    public SpecialtyRvAdapter(Context context) {
        this.context = context;
    }

    public void initData(List<SpecialtyBean.ResultBean> result) {
        this.result.addAll(result);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.specialty_group_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SpecialtyBean.ResultBean resultBean = result.get(position);
        holder.tv_group_specialty.setText(resultBean.getBigspecialty());
        Glide.with(context).load(resultBean.getIcon()).into(holder.img_group_specialty);

        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.rv_child_specialty.setLayoutManager(manager);
        List<SpecialtyBean.ResultBean.DataBean> data = resultBean.getData();
        adapter = new ChildSpecialtyRvAdapter(context,this);
        holder.rv_child_specialty.setAdapter(adapter);
        adapter.initData(data);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new ChildSpecialtyRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                OnGroupItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView rv_child_specialty;
        TextView tv_group_specialty;
        ImageView img_group_specialty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_group_specialty = itemView.findViewById(R.id.img_group_specialty);
            tv_group_specialty = itemView.findViewById(R.id.tv_group_specialty);
            rv_child_specialty = itemView.findViewById(R.id.rv_child_specialty);
        }
    }
    private OnGroupItemClickListener OnGroupItemClickListener;

    public void setOnItemClickListener(OnGroupItemClickListener onItemClickListener) {
        this.OnGroupItemClickListener = onItemClickListener;
    }

    public interface OnGroupItemClickListener{
        void onItemClick(int position);
    }
}
