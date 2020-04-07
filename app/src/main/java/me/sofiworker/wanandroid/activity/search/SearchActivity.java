package me.sofiworker.wanandroid.activity.search;

import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tamsiree.rxkit.RxKeyboardTool;

import butterknife.BindView;
import butterknife.OnClick;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.base.BaseActivity;
import me.sofiworker.wanandroid.fragment.search.SearchFragment;
import me.sofiworker.wanandroid.fragment.search.SearchResultFragment;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/26 11:51
 */

public class SearchActivity extends BaseActivity {

    @BindView(R.id.fl_search)
    FrameLayout mFrameLayout;
    @BindView(R.id.iv_back)
    ImageView mBackIv;
    @BindView(R.id.et_search)
    EditText mSearchEt;
    @BindView(R.id.tv_search)
    TextView mSearchTv;
    private SearchFragment mSearchFragment;
    private SearchResultFragment mSearchResultFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initThings() {
        mSearchFragment = new SearchFragment();
        mSearchResultFragment = new SearchResultFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_search, mSearchFragment)
                .add(R.id.fl_search, mSearchResultFragment)
                .show(mSearchFragment).hide(mSearchResultFragment).commit();
    }

    @OnClick({R.id.iv_back, R.id.tv_search})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_search:
                String keyWorld = mSearchEt.getText().toString();
                showResult(keyWorld, false);
                mSearchFragment.saveOrChangeHistory(keyWorld);
                break;
            default:
                break;
        }
    }

    public void showResult(String keyWorld, boolean isShowInEt) {
        if (isShowInEt) {
            mSearchEt.setText(keyWorld);
        }
        RxKeyboardTool.hideSoftInput(this);
        if (!keyWorld.isEmpty()) {
            // 结果碎片不可见则变为可见并加载数据
            if (!mSearchResultFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().show(mSearchResultFragment).hide(mSearchFragment).commit();
                mSearchResultFragment.loadData(keyWorld);
            }else {
                // 结果碎片可见则直接加载数据
                mSearchResultFragment.loadData(keyWorld);
            }
        }else {
            showToast(getString(R.string.write_something));
        }
    }

    @Override
    public void onBackPressed() {
        if (mSearchResultFragment.isVisible()) {
            mSearchEt.setText(null);
            getSupportFragmentManager().beginTransaction().show(mSearchFragment).hide(mSearchResultFragment).commit();
        }else {
            super.onBackPressed();
        }
    }
}
