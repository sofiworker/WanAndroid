package me.sofiworker.wanandroid.fragment.system.navigation;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.adapter.NavigationRvAdapter;
import me.sofiworker.wanandroid.base.BaseFragment;
import me.sofiworker.wanandroid.util.ActivityUtil;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/20 11:40
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {

    @BindView(R.id.rv_system_sub)
    RecyclerView mNavigationRv;
    private NavigationRvAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_system_sub;
    }

    @Override
    protected void initThings() {
        mPresenter = new NavigationPresenter();
        mPresenter.attachView(this);

        initRv();
        loadData();
    }

    private void loadData() {
        mPresenter.loadNav();
    }

    private void initRv() {
        mNavigationRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new NavigationRvAdapter(R.layout.rv_item_system_sub);
        mAdapter.setClickEvent(article -> ActivityUtil.goToArticleWeb(getContext(), article));
        mNavigationRv.setAdapter(mAdapter);
    }

    @Override
    public void showNav(List<Navigation> navigationList) {
        mAdapter.setNewData(navigationList);
    }
}
