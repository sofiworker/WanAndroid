package me.sofiworker.wanandroid.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableContainer;
import me.sofiworker.wanandroid.Api;
import me.sofiworker.wanandroid.util.RetrofitUtil;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/18 20:10
 */
public abstract class BasePresenter<T extends IView> implements IPresenter<T> {

    protected T mView;
    private CompositeDisposable mDisposableContainer;
    protected final Api mApi = RetrofitUtil.getRetrofit();

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
        if (mDisposableContainer != null && !mDisposableContainer.isDisposed()) {
            mDisposableContainer.clear();
            mDisposableContainer = null;
        }
    }

    @Override
    public void addDisposable(Disposable disposable){
        if (mDisposableContainer == null) {
            mDisposableContainer  = new CompositeDisposable();
        }
        mDisposableContainer.add(disposable);
    }
}
