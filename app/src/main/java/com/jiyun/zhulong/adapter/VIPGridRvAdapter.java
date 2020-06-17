package com.jiyun.zhulong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jiyun.bean.VIPBottomDataBean;
import com.jiyun.zhulong.R;

import java.util.ArrayList;
import java.util.List;

public class VIPGridRvAdapter extends RecyclerView.Adapter<VIPGridRvAdapter.ViewHolder> {
    private Context context;
    private List<VIPBottomDataBean.ResultBean.ListBean> list;

    public VIPGridRvAdapter(Context context, List<VIPBottomDataBean.ResultBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vip_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VIPBottomDataBean.ResultBean.ListBean listBean = list.get(position);
        holder.tv_comment_rate.setText(listBean.getComment_rate() + "好评");
        holder.tv_lesson_name.setText(listBean.getLesson_name());
        holder.tv_studentnum.setText(listBean.getStudentnum() + "学习");
        Glide.with(context).load(listBean.getVip_thumb()).into(holder.img_vip_thumb);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_vip_thumb;
        public TextView tv_lesson_name;
        public TextView tv_studentnum;
        public TextView tv_comment_rate;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img_vip_thumb = (ImageView) rootView.findViewById(R.id.img_vip_thumb);
            this.tv_lesson_name = (TextView) rootView.findViewById(R.id.tv_lesson_name);
            this.tv_studentnum = (TextView) rootView.findViewById(R.id.tv_studentnum);
            this.tv_comment_rate = (TextView) rootView.findViewById(R.id.tv_comment_rate);
        }

    }
}
