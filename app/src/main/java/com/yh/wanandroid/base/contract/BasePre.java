package com.yh.wanandroid.base.contract;

public interface BasePre<V extends BaseView> {
    void attachView(V view);

    void dettachView();
}
