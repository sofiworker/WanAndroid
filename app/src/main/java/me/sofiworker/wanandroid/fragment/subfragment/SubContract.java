package me.sofiworker.wanandroid.fragment.subfragment;

import java.util.List;

import me.sofiworker.wanandroid.base.IPresenter;
import me.sofiworker.wanandroid.base.IView;
import me.sofiworker.wanandroid.fragment.home.Article;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/21 20:07
 */
public interface SubContract {

    interface View extends IView {
        void showArticleList(List<Article> articles, boolean isOver);
    }

    interface Presenter extends IPresenter<View> {
        void loadWechatArticleList(int wechatId);
        void loadProjectArticleList(int projectId);
        void loadSubsystemArticleList(int subsystemId);
    }
}
