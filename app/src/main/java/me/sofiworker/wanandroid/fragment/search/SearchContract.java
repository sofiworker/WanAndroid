package me.sofiworker.wanandroid.fragment.search;

import java.util.List;

import me.sofiworker.wanandroid.base.IPresenter;
import me.sofiworker.wanandroid.base.IView;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/29 21:35
 */
public interface SearchContract {

    interface View extends IView {
        void showHotSearch(List<String> stringList);
        void showHistory(List<String> stringList, boolean isEmpty);
    }

    interface Presenter extends IPresenter<View> {
        void loadHotSearch();
        void loadHistory();
        void saveHistory(List<String> stringList);
        void clearAllHistory();
    }
}
