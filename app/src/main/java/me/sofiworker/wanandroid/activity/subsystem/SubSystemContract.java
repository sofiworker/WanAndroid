package me.sofiworker.wanandroid.activity.subsystem;

import me.sofiworker.wanandroid.base.IPresenter;
import me.sofiworker.wanandroid.base.IView;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/26 16:31
 */
public interface SubSystemContract {

    interface View extends IView {

    }

    interface Presenter extends IPresenter<View> {

    }
}
