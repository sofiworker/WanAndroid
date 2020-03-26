package me.sofiworker.wanandroid.fragment.home;

import java.util.List;

import me.sofiworker.wanandroid.base.IPresenter;
import me.sofiworker.wanandroid.base.IView;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/19 14:14
 */
public interface HomeContract {

    interface View extends IView {
        void showBanner(List<BannerData> bannerDataList);
        void showFirstArticlePage(List<Article> articles);
        void showMoreArticle(List<Article> articles, boolean isOver);
    }

    interface Presenter extends IPresenter<View> {
        void loadBanner();
        void loadFirstArticlePage();
        void loadMoreArticle();
    }
}
