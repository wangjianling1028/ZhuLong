package com.jiyun.zhulong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jiyun.bean.VIPBannerBean;
import com.jiyun.bean.VIPBottomDataBean;
import com.jiyun.zhulong.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class VIPAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<String> imgs = new ArrayList<>();
    private List<VIPBannerBean.ResultBean.LiveBeanX.LiveBean> live = new ArrayList<>();
    private List<VIPBottomDataBean.ResultBean.ListBean> list = new ArrayList<>();
    private static final int TYPE_ITEM_ONE = 1;
    private static final int TYPE_ITEM_TWO = 2;
    private static final int TYPE_ITEM_THREE = 3;

    public VIPAdapter(Context context) {
        this.context = context;
    }


    public void initBanner(List<String> imgs) {
        this.imgs.addAll(imgs);
        notifyDataSetChanged();
    }

    public void initLive(List<VIPBannerBean.ResultBean.LiveBeanX.LiveBean> live) {
        this.live.addAll(live);
        notifyDataSetChanged();
    }

    public void initList(List<VIPBottomDataBean.ResultBean.ListBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public List<VIPBottomDataBean.ResultBean.ListBean> getList() {
        return list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM_ONE) {
            View view = LayoutInflater.from(context).inflate(R.layout.vip_banner_layout, parent, false);
            return new OneViewHolder(view);
        } else if (viewType == TYPE_ITEM_TWO) {
            View view = LayoutInflater.from(context).inflate(R.layout.vip_horizontal_recyclerview, parent, false);
            return new TwoViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.vip_grid_recyclerview, parent, false);
            return new ThreeViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == TYPE_ITEM_ONE) {
            OneViewHolder oneViewHolder = (OneViewHolder) holder;
            oneViewHolder.vip_banner.setImages(imgs)
                    .setDelayTime(3000)
                    .isAutoPlay(true)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context).load(path).into(imageView);
                        }
                    }).start();
        } else if (itemViewType == TYPE_ITEM_TWO) {
            TwoViewHolder twoViewHolder = (TwoViewHolder) holder;
            LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
            twoViewHolder.vip_horiaontal_rv.setLayoutManager(manager);
            HoriaontalRvAdapter horiaontalRvAdapter = new HoriaontalRvAdapter(context, live);
            twoViewHolder.vip_horiaontal_rv.setAdapter(horiaontalRvAdapter);
        } else {
            ThreeViewHolder threeViewHolder = (ThreeViewHolder) holder;

            GridLayoutManager manager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return true;
                }
            };
            threeViewHolder.vip_grid_rv.setLayoutManager(manager);
            VIPGridRvAdapter vipGridRvAdapter = new VIPGridRvAdapter(context, list);
            threeViewHolder.vip_grid_rv.setAdapter(vipGridRvAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ITEM_ONE;
        } else if (position == 1) {
            return TYPE_ITEM_TWO;
        } else {
            return TYPE_ITEM_THREE;
        }
    }

    private class OneViewHolder extends RecyclerView.ViewHolder {

        private final Banner vip_banner;

        public OneViewHolder(View view) {
            super(view);
            vip_banner = view.findViewById(R.id.vip_banner);
        }
    }

    private class TwoViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView vip_horiaontal_rv;

        public TwoViewHolder(View view) {
            super(view);
            vip_horiaontal_rv = view.findViewById(R.id.vip_horiaontal_rv);
        }
    }

    private class ThreeViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView vip_grid_rv;

        public ThreeViewHolder(View view) {
            super(view);
            vip_grid_rv = view.findViewById(R.id.vip_grid_rv);
        }
    }
}
