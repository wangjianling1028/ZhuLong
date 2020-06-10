package com.jiyun.zhulong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jiyun.bean.DataSquadBean;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.interfaces.OnRecyclerItemClick;

import java.util.ArrayList;

public class DataSquadRVAdapter extends RecyclerView.Adapter<DataSquadRVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DataSquadBean.ResultBean> resultBeans;

    public static final int ITEM_TYPE=1,FOCUS_TYPE=2;

    public DataSquadRVAdapter(Context context, ArrayList<DataSquadBean.ResultBean> resultBeans) {
        this.context = context;
        this.resultBeans = resultBeans;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.datasquad_adapter, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataSquadBean.ResultBean resultBean = resultBeans.get(position);
        Glide.with(context).load(resultBean.getAvatar()).into(holder.url);
        holder.name.setText(resultBean.getGroup_name());
        holder.reshu.setText(resultBean.getMember_num()+"关注");
        holder.data.setText(resultBean.getIntroduce());


        holder.tvfocos.setText(resultBean.isFocus() ? "已关注" : "+关注");
        holder.tvfocos.setSelected(resultBean.isFocus() ? true : false);
        holder.tvfocos.setTextColor(resultBean.isFocus() ? ContextCompat.getColor(context,R.color.red) : ContextCompat.getColor(context,R.color.fontColorGray));

        holder.tvfocos.setOnClickListener(view -> {
            if (mOnRecyclerItemClick != null) mOnRecyclerItemClick.onItemClick(position,FOCUS_TYPE);
        });

        holder.itemView.setOnClickListener(view -> {
            if (mOnRecyclerItemClick != null) mOnRecyclerItemClick.onItemClick(position,ITEM_TYPE);
        });
    }

    private OnRecyclerItemClick mOnRecyclerItemClick;

    public void setmOnRecyclerItemClick(OnRecyclerItemClick pOnRecyclerItemClick) {
        mOnRecyclerItemClick = pOnRecyclerItemClick;
    }

    @Override
    public int getItemCount() {
        return resultBeans != null ?resultBeans.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView url;
        private final TextView name;
        private final TextView reshu;
        private final TextView data;
        private final TextView tvfocos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            url = itemView.findViewById(R.id.image_squad);
            name = itemView.findViewById(R.id.name_squad);
            reshu = itemView.findViewById(R.id.reshu_squad);
            data = itemView.findViewById(R.id.data_squad);
            tvfocos = itemView.findViewById(R.id.guanzhu_squad);
        }
    }
}
