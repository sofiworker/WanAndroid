package me.sofiworker.wanandroid.fragment.login;

import me.sofiworker.wanandroid.base.BaseObserver;
import me.sofiworker.wanandroid.base.BasePresenter;
import me.sofiworker.wanandroid.util.RxUtil;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/4/28 9:23
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Override
    public void login(String username, String password) {
        addDisposable(mApi.userLogin(username, password)
                .compose(RxUtil.changeRxThread()).subscribeWith(new BaseObserver<User>(mView) {
                    @Override
                    protected void onSuccess(User data) {
                        mView.isLoginSuccess(true);
                    }
                })
        );
    }

}
