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
import com.jiyun.bean.NewsEliteBean;
import com.jiyun.zhulong.R;

import java.util.ArrayList;

public class NewsEliteAdapter extends RecyclerView.Adapter<NewsEliteAdapter.ViewHolder> {
    private Context context;
    private ArrayList<NewsEliteBean.ResultBean> resultBeans;

    public NewsEliteAdapter(Context context, ArrayList<NewsEliteBean.ResultBean> resultBeans) {
        this.context = context;
        this.resultBeans = resultBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.newseilte_adapter, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsEliteBean.ResultBean resultBean = resultBeans.get(position);
        Glide.with(context).load(resultBean.getPic()).into(holder.url);
        holder.liulan.setText(resultBean.getView_num()+"人浏览");
        holder.huitie.setText(resultBean.getReply_num()+"回帖");
        holder.name.setText(resultBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return resultBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView url;
        private final TextView huitie;
        private final TextView liulan;
        private final TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_news);
            liulan = itemView.findViewById(R.id.liulan_news);
            huitie = itemView.findViewById(R.id.tie_news);
            url = itemView.findViewById(R.id.url_news);
        }
    }
}
