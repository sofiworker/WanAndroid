package me.sofiworker.wanandroid.fragment.wechat;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import me.sofiworker.wanandroid.base.BaseObserver;
import me.sofiworker.wanandroid.base.BasePresenter;
import me.sofiworker.wanandroid.util.LogUtil;
import me.sofiworker.wanandroid.util.RxUtil;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/21 19:42
 */
public class WechatPresenter extends BasePresenter<WechatContract.View> implements WechatContract.Presenter {

    @Override
    public void loadWechatData() {
        addDisposable(mApi.getWechatList().compose(RxUtil.changeRxThread()).subscribeWith(new BaseObserver<List<Wechat>>() {
            @Override
            protected void onSuccess(List<Wechat> data) {
                if (!data.isEmpty()) {
                    mView.showDataWithMi(data);
                }
            }
        }));
    }
}
