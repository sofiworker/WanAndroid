package me.sofiworker.wanandroid.activity.login;

import android.widget.ImageView;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.adapter.CommonVpAdapter;
import me.sofiworker.wanandroid.base.BaseActivity;
import me.sofiworker.wanandroid.base.BaseFragment;
import me.sofiworker.wanandroid.fragment.login.LoginFragment;
import me.sofiworker.wanandroid.fragment.register.RegisterFragment;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/29 20:25
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.iv_toolbar_back)
    ImageView mBackIv;
    @BindView(R.id.vp_login_register)
    ViewPager mMainVp;
    private CommonVpAdapter mVpAdapter;
    private List<BaseFragment> mFragmentList;

    @OnClick(R.id.iv_toolbar_back)
    void onClick() {
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initThings() {
        mVpAdapter = new CommonVpAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        LoginFragment loginFragment = new LoginFragment();
        RegisterFragment registerFragment = new RegisterFragment();
        mFragmentList = Arrays.asList(loginFragment, registerFragment);
        mVpAdapter.setFragmentList(mFragmentList);
        mMainVp.setAdapter(mVpAdapter);
    }
}
