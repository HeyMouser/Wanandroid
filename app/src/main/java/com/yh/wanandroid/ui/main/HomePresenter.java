package com.yh.wanandroid.ui.main;

import com.yh.wanandroid.base.contract.BasePresenter;
import com.yh.wanandroid.base.contract.BasePre;
import com.yh.wanandroid.bean.ArticalListBean;
import com.yh.wanandroid.bean.BannerBean;
import com.yh.wanandroid.bean.BaseResponse;
import com.yh.wanandroid.modle.ApiManager;
import com.yh.wanandroid.modle.ApiService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.HomePre {
    private HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void attachView(HomeContract.View view) {
        super.attachView(view);
    }

    @Override
    public void dettachView() {
        super.dettachView();
    }

    @Override
    public void getBanner() {
        ApiManager.createApi(ApiService.class)
                .getBannerList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<List<BannerBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<List<BannerBean>> listBaseResponse) {
                        if (listBaseResponse != null) {
                            if (listBaseResponse.getErrorCode() == 0) {
                                view.getBannerOk(listBaseResponse.getData());
                            } else {
                                view.getBannerErr(listBaseResponse.getErrorMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getBannerErr(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getArticalBeanList(int page) {
        ApiManager.createApi(ApiService.class)
                .getArticalList(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse<ArticalListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<ArticalListBean> listBaseResponse) {
                        if (listBaseResponse != null) {
                            if (listBaseResponse.getErrorCode() == 0) {
                                view.getArticalBeanListOk(listBaseResponse.getData());
                            } else {
                                view.getArticalBeanListErr(listBaseResponse.getErrorMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.getArticalBeanListErr(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
