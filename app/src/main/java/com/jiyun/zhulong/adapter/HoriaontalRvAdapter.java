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
import com.bumptech.glide.request.RequestOptions;
import com.jiyun.bean.VIPBannerBean;
import com.jiyun.zhulong.R;

import java.util.ArrayList;
import java.util.List;


public class HoriaontalRvAdapter extends RecyclerView.Adapter<HoriaontalRvAdapter.ViewHolder> {
    private Context context;
    private List<VIPBannerBean.ResultBean.LiveBeanX.LiveBean> live = new ArrayList<>();

    public HoriaontalRvAdapter(Context context, List<VIPBannerBean.ResultBean.LiveBeanX.LiveBean> live) {
        this.context = context;
        this.live = live;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vip_live_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VIPBannerBean.ResultBean.LiveBeanX.LiveBean liveBean = live.get(position);
        holder.tv_activityName.setText(liveBean.getActivityName());
        holder.tv_start_date.setText(liveBean.getStart_date());
        Glide.with(context).load(liveBean.getTeacher_photo()).apply(new RequestOptions().circleCrop()).into(holder.civ_teacher_photo);
    }

    @Override
    public int getItemCount() {
        return live.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView civ_teacher_photo;
        private final TextView tv_start_date;
        private final TextView tv_activityName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_activityName = itemView.findViewById(R.id.tv_activityName);
            tv_start_date = itemView.findViewById(R.id.tv_start_date);
            civ_teacher_photo = itemView.findViewById(R.id.civ_teacher_photo);
        }
    }
}
