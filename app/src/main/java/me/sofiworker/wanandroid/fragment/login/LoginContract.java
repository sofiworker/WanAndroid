package me.sofiworker.wanandroid.fragment.login;

import me.sofiworker.wanandroid.base.IPresenter;
import me.sofiworker.wanandroid.base.IView;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/4/28 9:23
 */
public interface LoginContract {

    interface View extends IView {
        void isLoginSuccess(boolean isSuccess);
    }

    interface Presenter extends IPresenter<View> {
        void login(String username, String password);
    }
}
