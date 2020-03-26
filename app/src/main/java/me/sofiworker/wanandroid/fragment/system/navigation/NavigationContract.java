package me.sofiworker.wanandroid.fragment.system.navigation;

import java.util.List;

import me.sofiworker.wanandroid.base.IPresenter;
import me.sofiworker.wanandroid.base.IView;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/20 11:44
 */
public interface NavigationContract {

    interface View extends IView {
        void showNav(List<Navigation> navigationList);
    }

    interface Presenter extends IPresenter<View> {
        void loadNav();
    }
}
