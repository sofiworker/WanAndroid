package me.sofiworker.wanandroid.activity.search;

import android.widget.FrameLayout;

import butterknife.BindView;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.base.BaseActivity;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/26 11:51
 */

public class SearchActivity extends BaseActivity {

    @BindView(R.id.fl_search)
    FrameLayout mFrameLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initThings() {

    }
}
