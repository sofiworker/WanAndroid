package me.sofiworker.wanandroid.fragment.search;

import java.util.List;

import me.sofiworker.wanandroid.base.IPresenter;
import me.sofiworker.wanandroid.base.IView;
import me.sofiworker.wanandroid.fragment.home.Article;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/29 22:41
 */
public interface SearchResultContract {

    interface View extends IView {
        void showArticleList(List<Article> articles, boolean isOver, boolean isNew);
    }

    interface Presenter extends IPresenter<View> {
        void loadResult(String str, boolean isNew);
    }
}
