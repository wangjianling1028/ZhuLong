package com.jiyun.zhulong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.jiyun.bean.GroupDetailEntity;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.interfaces.OnRecyclerItemClick;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupDetailCenterTabAdapter extends RecyclerView.Adapter<GroupDetailCenterTabAdapter.ViewHolder> {

    private Context mContext;
    private List<GroupDetailEntity.Tag> mTabListData;

    public GroupDetailCenterTabAdapter(Context pContext, List<GroupDetailEntity.Tag> pTabListData) {
        mContext = pContext;
        mTabListData = pTabListData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.group_detail_tab_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tagContent.setText(mTabListData.get(position).getTag_name());
        holder.fallsView.setVisibility(View.INVISIBLE);

        GroupDetailEntity.Tag tag = mTabListData.get(position);
        boolean tabSelected = tag.isSelecting();
        if (tag.getContainsName() == null)tag.setContainsName(new ArrayList<>());
        if (tag.getOn() == 1){
            for (int i =0 ;i<tag.getSelects().size();i++){
                if (tag.getSelects().get(i).getOn() == 1){
                    List<String> containsName = tag.getContainsName();
                    containsName.add(tag.getSelects().get(i).getName());
                    tag.setContainsName(containsName);
                    break;
                }
            }
        }

        holder.tagContent.setText(tag.getContainsName().size() == 0 ? tag.getTag_name() : tag.getContainsName().get(0));
        holder.tagContent.setBackground(ContextCompat.getDrawable(mContext,tag.getContainsName().size() != 0 && !tabSelected ? R.drawable.group_tab_bg_has_selected_content : R.drawable.group_tab_bg));
        holder.fallsView.setVisibility(tabSelected ? View.VISIBLE : View.INVISIBLE);
        holder.tagContent.setTextColor(ContextCompat.getColor(mContext,tabSelected ? R.color.red : tag.getContainsName().size() != 0? R.color.red : R.color.black));
        holder.tagContent.setCompoundDrawablesWithIntrinsicBounds(0,0,tabSelected ? R.drawable.ic_menu_arrow_up_red : tag.getContainsName().size() != 0?R.drawable.ic_menu_arrow_down_red:R.drawable.ic_menu_arrow_down_gray,0);
        holder.tagContent.setOnClickListener(v -> {
            if (mOnRecyclerItemClick != null)mOnRecyclerItemClick.onItemClick(position);
        });


    }

    private OnRecyclerItemClick mOnRecyclerItemClick;
    public void setmOnRecyclerItemClick(OnRecyclerItemClick pOnRecyclerItemClick){
        mOnRecyclerItemClick = pOnRecyclerItemClick;
    }

    @Override
    public int getItemCount() {
        return mTabListData != null ? mTabListData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.falls_view)
        View fallsView;
        @BindView(R.id.tagContent)
        TextView tagContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
