package me.sofiworker.wanandroid.activity.main;

import me.sofiworker.wanandroid.base.IPresenter;
import me.sofiworker.wanandroid.base.IView;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/18 21:01
 */
public interface MainContract {

    interface View extends IView {
    }

    interface Presenter extends IPresenter<View> {
    }
}
