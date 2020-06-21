package com.jiyun.course.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jiyun.bean.CourseDrillBean;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.interfaces.OnRecyclerItemClick;

import java.util.ArrayList;
import java.util.List;

/**
 *    ：      --
 * 创建于： 2020/6/6 01:42
 *    邮箱：1750827655@qq.com
 */
public class ChildCourseRvAdapter extends RecyclerView.Adapter<ChildCourseRvAdapter.ViewHolder> {
    private Context context;
    private List<CourseDrillBean.ResultBean.ListsBean> lists = new ArrayList<>();

    public void initData(List<CourseDrillBean.ResultBean.ListsBean> lists) {
        this.lists.addAll(lists);
        notifyDataSetChanged();
    }

    public List<CourseDrillBean.ResultBean.ListsBean> getLists() {
        return lists;
    }

    public ChildCourseRvAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseDrillBean.ResultBean.ListsBean listsBean = lists.get(position);
        holder.tv_lesson_name.setText(listsBean.getLesson_name());
        Glide.with(context).load(listsBean.getThumb()).into(holder.img_thumb);
        holder.studentnum.setText(listsBean.getStudentnum()+"人学习");
        holder.tv_rate.setText("好评度"+listsBean.getRate());
        holder.tv_price.setText("￥"+listsBean.getPrice());
        //接口回调 点击进入详情
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnRecyclerItemClick.onItemClick(position);
            }
        });

    }

    private OnRecyclerItemClick mOnRecyclerItemClick;

    public void setmOnRecyclerItemClick(OnRecyclerItemClick pOnRecyclerItemClick) {
        this.mOnRecyclerItemClick = pOnRecyclerItemClick;
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_thumb;
        public TextView tv_lesson_name;
        public TextView studentnum;
        public TextView tv_rate;
        public TextView tv_price;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img_thumb = (ImageView) rootView.findViewById(R.id.img_thumb);
            this.tv_lesson_name = (TextView) rootView.findViewById(R.id.tv_lesson_name);
            this.studentnum = (TextView) rootView.findViewById(R.id.studentnum);
            this.tv_rate = (TextView) rootView.findViewById(R.id.tv_rate);
            this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
        }

    }
}
