package me.sofiworker.wanandroid.fragment.system.knowledge;

import java.util.List;

import me.sofiworker.wanandroid.base.IPresenter;
import me.sofiworker.wanandroid.base.IView;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/20 11:41
 */
public interface KnowledgeContract {

    interface View extends IView {
        void showKnowledgeData(List<Knowledge> data);
    }

    interface Presenter extends IPresenter<View> {
        void loadKnowledgeData();
    }
}
