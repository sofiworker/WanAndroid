package me.sofiworker.wanandroid;

import java.util.List;

import io.reactivex.Observable;
import me.sofiworker.wanandroid.base.BaseData;
import me.sofiworker.wanandroid.fragment.home.Article;
import me.sofiworker.wanandroid.fragment.home.ArticlePage;
import me.sofiworker.wanandroid.fragment.home.BannerData;
import me.sofiworker.wanandroid.fragment.project.Project;
import me.sofiworker.wanandroid.fragment.system.knowledge.Knowledge;
import me.sofiworker.wanandroid.fragment.system.navigation.Navigation;
import me.sofiworker.wanandroid.fragment.wechat.Wechat;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/18 20:09
 */
public interface Api {

    @GET("/banner/json")
    Observable<BaseData<List<BannerData>>> getBanner();

    @GET("/article/list/{num}/json")
    Observable<BaseData<ArticlePage>> getArticleListByPage(@Path("num") int num);

    @GET("/article/top/json")
    Observable<BaseData<List<Article>>> getTopArticleList();

    @GET("/tree/json")
    Observable<BaseData<List<Knowledge>>> getKnowledge();

    @GET("/navi/json")
    Observable<BaseData<List<Navigation>>> getNav();

    @GET("/wxarticle/chapters/json")
    Observable<BaseData<List<Wechat>>> getWechatList();

    @GET("/wxarticle/list/{id}/{pageNum}/json")
    Observable<BaseData<ArticlePage>> getWechatArticle(@Path("id") int id, @Path("pageNum") int pageNum);

    @GET("/project/tree/json")
    Observable<BaseData<List<Project>>> getProjectList();

    @GET("/project/list/{pageNum}/json")
    Observable<BaseData<ArticlePage>> getProjectArticleList(@Path("pageNum") int pageNum, @Query("cid") int cid);

    @GET("/article/list/{pageNum}/json")
    Observable<BaseData<ArticlePage>> getSubsystemArticleList(@Path("pageNum") int pageNum, @Query("cid") int cid);
}
