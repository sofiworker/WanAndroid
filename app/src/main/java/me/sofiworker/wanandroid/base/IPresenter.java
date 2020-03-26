package me.sofiworker.wanandroid.base;

import io.reactivex.disposables.Disposable;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/18 19:20
 */
public interface IPresenter<T extends IView> {

    /**
     * 绑定视图
     * @param view 视图
     */
    void attachView(T view);

    /**
     * 解绑视图
     */
    void detachView();

    void addDisposable(Disposable disposable);
}
