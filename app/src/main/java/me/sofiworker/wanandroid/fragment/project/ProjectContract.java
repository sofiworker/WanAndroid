package me.sofiworker.wanandroid.fragment.project;

import java.util.List;

import me.sofiworker.wanandroid.base.IPresenter;
import me.sofiworker.wanandroid.base.IView;
import me.sofiworker.wanandroid.fragment.system.knowledge.Knowledge;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/24 8:55
 */
public interface ProjectContract {

    interface View extends IView {
        void showProjectTitleList(List<Project> data);
    }

    interface Presenter extends IPresenter<View> {
        void loadProjectTitle();
    }
}
