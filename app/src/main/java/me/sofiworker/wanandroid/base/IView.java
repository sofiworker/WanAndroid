package me.sofiworker.wanandroid.base;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/18 19:19
 */
public interface IView {

    void showToast(String msg);

    void showDialog(String msg);

    void showLoading(String msg);

    void showError(String msg);

    void showNoNetwork();

    void showEmpty();
}
