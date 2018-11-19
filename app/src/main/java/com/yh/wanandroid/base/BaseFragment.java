package com.yh.wanandroid.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ldoublem.loadingviewlib.LVChromeLogo;
import com.yh.wanandroid.R;
import com.yh.wanandroid.base.contract.BaseView;
import com.yh.wanandroid.utils.net.NetWorkBroadcastReceiver;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements BaseView, NetWorkBroadcastReceiver.NetEvent {

    protected Activity activity;
    protected BaseApplication mContext;
    private Unbinder unbinder;
    private int netMobile;

    private static final int NORMAL_STATUS = 0;
    private static final int LOADING_STATUS = 1;
    private static final int ERROR_STATUS = 2;
    private static final int EMPTY_STATUS = 3;

    private View mErrorView;
    private View mLoadingView;
    private View mEmptyView;
    private LVChromeLogo lvChromeLogo;//加载view
    private ViewGroup mNormalView;
    private TextView tvMsg;

    private int currentState = NORMAL_STATUS;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();
        mContext = BaseApplication.getInstance();
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResID(), container, false);
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    public abstract int getLayoutResID();

    public abstract int initData();

    protected void initView() {
        if (getView() == null)
            return;
        mNormalView = getView().findViewById(R.id.normal_view);
        if (mNormalView == null) {
            throw new IllegalStateException("The subclass of RootActivity must contain a View named 'mNormalView'.");
        }
        if (!(mNormalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException("mNormalView's ParentView should be a ViewGroup.");
        }
        ViewGroup parent = (ViewGroup) mNormalView.getParent();
        View.inflate(activity, R.layout.view_loading, parent);
        View.inflate(activity, R.layout.view_error, parent);
        View.inflate(activity, R.layout.view_empty, parent);
        mLoadingView = parent.findViewById(R.id.loading_group);
        lvChromeLogo = mLoadingView.findViewById(R.id.lv_load);
        mErrorView = parent.findViewById(R.id.error_group);
        mEmptyView = parent.findViewById(R.id.empty_group);
        tvMsg = parent.findViewById(R.id.tv_err_msg);
        mErrorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload();
            }
        });
        mErrorView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mNormalView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNetChange(int netMobile) {
        this.netMobile = netMobile;

    }

    @Override
    public void showNormal() {
        if (currentState == NORMAL_STATUS) {
            return;
        }
        hideCurrentView();
        currentState = NORMAL_STATUS;
        mNormalView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String err) {
        if (currentState == ERROR_STATUS) {
            return;
        }
        hideCurrentView();
        currentState = ERROR_STATUS;
        mErrorView.setVisibility(View.VISIBLE);
        tvMsg.setText(err);
    }

    @Override
    public void showLoading() {
        if (currentState == LOADING_STATUS) {
            return;
        }
        hideCurrentView();
        currentState = LOADING_STATUS;
        mLoadingView.setVisibility(View.VISIBLE);
        lvChromeLogo.startAnim();
    }

    @Override
    public void showEmpty() {
        if (currentState == EMPTY_STATUS) {
            return;
        }
        hideCurrentView();
        currentState = EMPTY_STATUS;
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void reload() {

    }
    private void hideCurrentView() {
        switch (currentState) {
            case NORMAL_STATUS:
                if (mNormalView == null) {
                    return;
                }
                mNormalView.setVisibility(View.GONE);
                break;
            case LOADING_STATUS:
                lvChromeLogo.stopAnim();
                mLoadingView.setVisibility(View.GONE);
                break;
            case ERROR_STATUS:
                mErrorView.setVisibility(View.GONE);
                break;
            case EMPTY_STATUS:
                mEmptyView.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
