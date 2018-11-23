package com.yh.wanandroid.modle;

import com.yh.wanandroid.bean.ArticalListBean;
import com.yh.wanandroid.bean.BannerBean;
import com.yh.wanandroid.bean.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    /**
     * 获取首页banner
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerBean>>> getBannerList();

    /**
     * 获取首页文章列表
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<ArticalListBean>> getArticalList(@Path("page") int page);
}
