package com.yh.wanandroid.ui.dashboard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yh.wanandroid.R;
import com.yh.wanandroid.base.BaseFragment;

public class DashboardFragment extends BaseFragment{
    private static DashboardFragment dashboardFragment;
    public static DashboardFragment getInstance(){
        if (dashboardFragment == null)
            dashboardFragment = new DashboardFragment();
        return dashboardFragment;
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_dashboard;
    }

    @Override
    public int initData() {
        return 0;
    }
}