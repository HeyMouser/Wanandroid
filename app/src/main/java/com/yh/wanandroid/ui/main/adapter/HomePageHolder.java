package com.yh.wanandroid.ui.main.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.yh.wanandroid.R;

import butterknife.BindView;



public class HomePageHolder extends BaseViewHolder {

    @BindView(R.id.tv_author)
    TextView mTvAuthor;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.image_collect)
    ImageView mImageCollect;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_tag)
    TextView mTvTag;

    public HomePageHolder(View view) {
        super(view);
    }

}
