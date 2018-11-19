package com.yh.wanandroid.base.contract;

public class BasePresenter<V extends BaseView> implements BasePre<V>{
    protected V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void dettachView() {
        this.view = null;
    }
}
