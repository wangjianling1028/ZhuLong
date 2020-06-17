package com.jiyun.zhulong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.jiyun.bean.GroupDetailEntity;
import com.jiyun.zhulong.R;
import com.yiyatech.utils.newAdd.GlideUtil;
import com.yiyatech.utils.newAdd.TimeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DataGroupDetailBottomAdapter extends RecyclerView.Adapter<DataGroupDetailBottomAdapter.ViewHolder> {

    private Context mContext;
    private List<GroupDetailEntity.Thread> mList;

    public DataGroupDetailBottomAdapter(Context pContext, List<GroupDetailEntity.Thread> pList) {
        mContext = pContext;
        mList = pList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homepage_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupDetailEntity.Thread thread = mList.get(position);
        holder.tvTitleLeft.setText(thread.getTitle());
        holder.tvBrowseNum.setText(thread.getView_num()+"人浏览");
        holder.tvCommentNum.setText(thread.getReply_num()+"人跟帖");
        GlideUtil.loadCornerImage(holder.ivPhoto,thread.getPic(),null,6f);
        holder.tvAuthorAndTime.setText(TimeUtil.parseTimeYMD(thread.getCreate_time()));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title_left)
        TextView tvTitleLeft;
        @BindView(R.id.tv_browse_num)
        TextView tvBrowseNum;
        @BindView(R.id.tv_comment_num)
        TextView tvCommentNum;
        @BindView(R.id.iv_photo)
        ImageView ivPhoto;
        @BindView(R.id.tv_author_and_time)
        TextView tvAuthorAndTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
