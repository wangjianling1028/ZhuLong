package com.jiyun.zhulong.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jiyun.bean.BannerLiveInfo;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.design.RoundImage;
import com.yiyatech.utils.newAdd.GlideUtil;
import com.yiyatech.utils.newAdd.TimeUtil;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2020/6/8.
 */
public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.ViewHolder> {

    private List<BannerLiveInfo.Live> liveData;
    private Context mContext;

    public LiveAdapter(List<BannerLiveInfo.Live> pLiveData, Context pContext) {
        liveData = pLiveData;
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.live_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BannerLiveInfo.Live live = liveData.get(position);
        GlideUtil.loadImage(holder.roundPhoto,live.teacher_photo);
        holder.title.setText(live.activityName);
        if (!TextUtils.isEmpty(live.startTime))holder.time.setText(TimeUtil.formatLiveTime(Long.parseLong(live.startTime)));
    }

    @Override
    public int getItemCount() {
        return liveData != null ? liveData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.round_photo)
        RoundImage roundPhoto;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
