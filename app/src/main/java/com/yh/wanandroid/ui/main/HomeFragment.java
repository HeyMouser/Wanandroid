package com.yh.wanandroid.ui.main;

import com.yh.wanandroid.R;
import com.yh.wanandroid.base.BaseFragment;

public class HomeFragment extends BaseFragment {

    private static HomeFragment homeFragment;

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
    public int initData() {
        return 0;
    }
}