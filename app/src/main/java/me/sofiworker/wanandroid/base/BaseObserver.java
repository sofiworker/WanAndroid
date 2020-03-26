package me.sofiworker.wanandroid.base;

import com.tamsiree.rxkit.RxTool;
import com.tamsiree.rxkit.view.RxToast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceObserver;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.util.LogUtil;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/18 20:31
 */
public abstract class BaseObserver<T> extends ResourceObserver<BaseData<T>> {

    private IView mView;

    public BaseObserver() {}

    public BaseObserver(IView view){
        this.mView = view;
    }

    @Override
    protected void onStart() {

    }

    @Override
    public void onNext(BaseData<T> baseData) {
        if (baseData.getErrorCode() == 0) {
            onSuccess(baseData.getData());
        }else if (baseData.getErrorCode() == -1001){
            // TODO: 2020/3/18 登录跳转
        }else {
            onFail(baseData.getErrorMsg());
        }
    }

    @Override
    public void onError(Throwable e) {
//        mView.showError(e.getMessage());
        LogUtil.d(e.getMessage());
        RxToast.error(e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    /**
     * 请求成功回调
     * @param data 最终结果
     */
    protected abstract void onSuccess(T data);

    protected void onFail(String msg){
        RxToast.error(msg);
    }
}
