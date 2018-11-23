package com.yh.wanandroid.ui.main;

import com.yh.wanandroid.base.contract.BasePre;
import com.yh.wanandroid.base.contract.BasePresenter;
import com.yh.wanandroid.base.contract.BaseView;
import com.yh.wanandroid.bean.ArticalListBean;
import com.yh.wanandroid.bean.BannerBean;

import java.util.List;

public class HomeContract {
    public static interface View extends BaseView {
        void getBannerOk(List<BannerBean> bannerBeanList);

        void getBannerErr(String err);

        void getArticalBeanListOk(ArticalListBean articalListBeanList);

        void getArticalBeanListErr(String err);
    }

    public interface HomePre extends BasePre<View> {
        void getBanner();

        void getArticalBeanList(int page);
    }
}
