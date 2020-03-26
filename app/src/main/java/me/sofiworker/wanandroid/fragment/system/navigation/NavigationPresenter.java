package me.sofiworker.wanandroid.fragment.system.navigation;

import java.util.List;

import me.sofiworker.wanandroid.base.BaseObserver;
import me.sofiworker.wanandroid.base.BasePresenter;
import me.sofiworker.wanandroid.util.RxUtil;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/20 11:45
 */
public class NavigationPresenter extends BasePresenter<NavigationContract.View> implements NavigationContract.Presenter {


    @Override
    public void loadNav() {
        addDisposable(mApi.getNav().compose(RxUtil.changeRxThread()).subscribeWith(new BaseObserver<List<Navigation>>() {
            @Override
            protected void onSuccess(List<Navigation> data) {
                if (!data.isEmpty()) {
                    mView.showNav(data);
                }
            }
        }));
    }
}
