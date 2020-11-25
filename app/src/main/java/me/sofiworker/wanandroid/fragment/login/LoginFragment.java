package me.sofiworker.wanandroid.fragment.login;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.OnClick;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.base.BaseFragment;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/4/16 10:04
 */
public class LoginFragment extends BaseFragment<LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.et_account)
    TextInputEditText mAccountEt;
    @BindView(R.id.et_password)
    TextInputEditText mPasswordEt;
    @BindView(R.id.btn_login)
    Button mLoginBtn;

    @OnClick(R.id.btn_login)
    void onClick() {
        if (!TextUtils.isEmpty(mAccountEt.getText()) && !TextUtils.isEmpty(mPasswordEt.getText())) {
            mPresenter.login(mAccountEt.getText().toString(), mPasswordEt.getText().toString());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initThings() {
        mPresenter = new LoginPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void isLoginSuccess(boolean isSuccess) {
        if (isSuccess) {
            if (getActivity() != null) {
                getActivity().finish();
            }
        }
    }

//    @Override
//    public void showToast(String msg) {
//        Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER, 0 , 0);
//        toast.show();
//    }
}
