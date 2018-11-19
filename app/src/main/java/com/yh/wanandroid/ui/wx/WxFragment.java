package com.yh.wanandroid.ui.wx;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yh.wanandroid.R;
import com.yh.wanandroid.base.BaseFragment;


public class WxFragment extends BaseFragment {

    private static WxFragment wxFragment;
    public static WxFragment getInstance(){
        if (wxFragment == null)
            wxFragment = new WxFragment();
        return wxFragment;
    }
    @Override
    public int getLayoutResID() {
        return R.layout.fragment_wx;
    }

    @Override
    public int initData() {
        return 0;
    }
}
