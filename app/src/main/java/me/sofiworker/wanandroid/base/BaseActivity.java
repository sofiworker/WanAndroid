package me.sofiworker.wanandroid.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.tamsiree.rxkit.view.RxToast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.sofiworker.wanandroid.R;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/18 19:18
 */
public abstract class BaseActivity<T extends BasePresenter> extends FragmentActivity implements IView {

    protected T mPresenter;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ImmersionBar.with(this).fitsSystemWindows(true)
                .statusBarColor(R.color.colorPrimary).init();
        mBind = ButterKnife.bind(this);
        initThings();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
            mBind = null;
        }
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    /**
     * 获取视图id
     * @return 视图id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initThings();

    @Override
    public void showToast(String msg) {
        RxToast.showToast(msg);
    }

    @Override
    public void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setTitle(getString(R.string.prompt))
                .setPositiveButton(getString(R.string.confirm), (dialog, which) -> {
                    builder.create().dismiss();
                })
                .setNegativeButton(getString(R.string.cancel), null);
        builder.create().show();
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showNoNetwork() {

    }

    @Override
    public void showEmpty() {

    }
}
