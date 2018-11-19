package com.yh.wanandroid.base.contract;

public interface BaseView {
    void showNormal();

    void showError(String msg);

    void showLoading();

    void showEmpty();

    void reload();
}
