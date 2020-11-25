package me.sofiworker.wanandroid.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tamsiree.rxkit.view.RxToast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/18 19:20
 */
public abstract class BaseFragment<T extends IPresenter<?>> extends Fragment implements IView {

    protected T mPresenter;
    protected View mRootView;
    private Unbinder mBind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), container, false);
        mRootView = inflate;
        mBind = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initThings();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

    protected abstract void initThings();

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void showError(String msg) {
        RxToast.showToast(msg);
    }

    @Override
    public void showNoNetwork() {

    }

    @Override
    public void showEmpty() {

    }
}
