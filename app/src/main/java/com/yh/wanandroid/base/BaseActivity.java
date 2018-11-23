package com.yh.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yh.wanandroid.utils.ActivityManagerUtil;
import com.yh.wanandroid.utils.LogMessageUtil;
import com.yh.wanandroid.utils.net.NetUtil;
import com.yh.wanandroid.utils.net.NetWorkBroadcastReceiver;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements NetWorkBroadcastReceiver.NetEvent {
    public static NetWorkBroadcastReceiver.NetEvent netEvent;
    public ActivityManagerUtil activityManagerUtil = ActivityManagerUtil.getActivityManager();
    protected BaseApplication mContext;
    protected BaseActivity mActivity;
    public Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        activityManagerUtil.add(this);
        unbinder = ButterKnife.bind(this);
        mContext = BaseApplication.getInstance();
        mActivity = this;
        netEvent = this;
        initView();
        initToolBar();
        initData();
    }

    protected abstract int setLayoutId();

    protected abstract void initView();

    protected abstract void initToolBar();

    protected abstract void initData();

    @Override
    public void onNetChange(int netMobile) {
        if (netMobile == NetUtil.NETWORK_NONE) {
            LogMessageUtil.e("NETWORK_NONE");
        } else {
            LogMessageUtil.e("NETWORK_NORMAL");
        }
    }

    @Override
    protected void onDestroy() {
        activityManagerUtil.remove(this);
        unbinder.unbind();
        super.onDestroy();
    }
}
