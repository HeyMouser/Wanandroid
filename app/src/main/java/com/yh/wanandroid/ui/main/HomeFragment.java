package com.yh.wanandroid.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yh.wanandroid.R;
import com.yh.wanandroid.base.BaseFragment;
import com.yh.wanandroid.bean.ArticalListBean;
import com.yh.wanandroid.bean.BannerBean;
import com.yh.wanandroid.ui.main.adapter.HomePageAdapter;
import com.yh.wanandroid.utils.GlideImageLoader;
import com.yh.wanandroid.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment implements HomeContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;

    private HomePresenter homePresenter;
    private static HomeFragment homeFragment;

    private LinearLayout bannerView;
    private Banner banner;

    private List<ArticalListBean.DatasBean> articalList;
    private List<String> linkList;
    private List<String> imageList;
    private List<String> titleList;

    private HomePageAdapter mAdapter;

    public static HomeFragment getInstance() {
        if (homeFragment == null)
            homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        bannerView = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_banner,null);
        banner = bannerView.findViewById(R.id.banner);
        bannerView.removeView(banner);
        bannerView.addView(banner);
    }

    @Override
    public void initData() {
        articalList = new ArrayList<>();
        linkList = new ArrayList<>();
        imageList = new ArrayList<>();
        titleList = new ArrayList<>();

        mAdapter = new HomePageAdapter(R.layout.item_homepage,articalList);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.addHeaderView(bannerView);
        recyclerView.setAdapter(mAdapter);

        homePresenter = new HomePresenter(this);
        homePresenter.getBanner();
        homePresenter.getArticalBeanList(0);
    }

    @Override
    public void getBannerOk(List<BannerBean> bannerBeanList) {
        imageList.clear();
        titleList.clear();
        linkList.clear();
        for(BannerBean benarBean:bannerBeanList){
            imageList.add(benarBean.getImagePath());
            titleList.add(benarBean.getTitle());
            linkList.add(benarBean.getUrl());
        }
        if(!activity.isDestroyed()){
            // banner git 地址 https://github.com/youth5201314/banner
            banner.setImageLoader(new GlideImageLoader())
                    .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                    .setImages(imageList)
                    .setBannerAnimation(Transformer.Accordion)
                    .setBannerTitles(titleList)
                    .isAutoPlay(true)
                    .setDelayTime(5000)
                    .setIndicatorGravity(BannerConfig.RIGHT)
                    .start();
        }
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                if(!TextUtils.isEmpty(linkList.get(position))){
//                    Bundle bundle = new Bundle();
//                    bundle.putString(ConstantUtil.HOME_DETAIL_TITLE,titleList.get(position));
//                    bundle.putString(ConstantUtil.HOME_DETAIL_PATH,linkList.get(position));
//                    JumpUtil.overlay(context, HomeDetailActivity.class,bundle);
//                }
            }
        });
    }

    @Override
    public void getBannerErr(String err) {

    }

    @Override
    public void getArticalBeanListOk(ArticalListBean articalListBeanList) {
        articalList.addAll(articalListBeanList.getDatas());
        mAdapter.addData(articalList);
    }

    @Override
    public void getArticalBeanListErr(String err) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }
}