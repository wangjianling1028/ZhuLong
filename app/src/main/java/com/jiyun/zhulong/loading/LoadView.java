
package com.jiyun.zhulong.loading;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jiyun.zhulong.R;


/**
 * 带提示内容的进度框
 */
public class LoadView extends Dialog {

    private ProgressImageView mProgressView;

    private TextView mTvContent;

    private String mContent;
    private static LoadView sLoadView;

    public static LoadView getInstance(Context context, String content) {
        if (sLoadView == null)
            sLoadView = new LoadView(context, content);
        return sLoadView;
    }

    public LoadView(Context context, String content) {
        super(context, R.style.DialogStyle);
        mContent = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress_content);
        mProgressView = findViewById(R.id.view_list_empty_progress);
        mTvContent = findViewById(R.id.tv_content);
        mTvContent.setText(TextUtils.isEmpty(mContent) ? "加载中" : mContent);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mProgressView != null)
            mProgressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mProgressView != null)
            mProgressView.setVisibility(View.GONE);
    }
}
