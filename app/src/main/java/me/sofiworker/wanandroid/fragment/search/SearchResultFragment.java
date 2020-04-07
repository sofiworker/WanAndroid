package me.sofiworker.wanandroid.fragment.search;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.adapter.CommonRvAdapter;
import me.sofiworker.wanandroid.base.BaseFragment;
import me.sofiworker.wanandroid.fragment.home.Article;
import me.sofiworker.wanandroid.util.ActivityUtil;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/26 14:43
 */
public class SearchResultFragment extends BaseFragment<SearchResultContract.Presenter> implements SearchResultContract.View {

    @BindView(R.id.rv_search_result)
    RecyclerView mResultRv;
    private CommonRvAdapter mAdapter;

    private String mSearchStr;

    private List<Article> mArticleList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_result;
    }

    @Override
    protected void initThings() {
        mPresenter = new SearchResultPresenter();
        mPresenter.attachView(this);

        mResultRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CommonRvAdapter(R.layout.rv_item_common);
        mResultRv.setAdapter(mAdapter);
        if (mAdapter.getLoadMoreModule() != null) {
            mAdapter.getLoadMoreModule().setOnLoadMoreListener(() -> mPresenter.loadResult(mSearchStr, false));
        }
        mAdapter.setOnItemClickListener((adapter, view, position) -> ActivityUtil.goToArticleWeb(getContext(), mArticleList.get(position)));
    }

    public void loadData(String searchStr) {
        this.mSearchStr = searchStr;
        mPresenter.loadResult(searchStr, true);
    }

    @Override
    public void showArticleList(List<Article> articles, boolean isOver, boolean isNew) {
        if (isNew) {
            mArticleList.clear();
            mAdapter.setNewData(articles);
        }else {
            mAdapter.addData(articles);
        }
        mArticleList.addAll(articles);
        if (mAdapter.getLoadMoreModule() != null) {
            mAdapter.getLoadMoreModule().loadMoreComplete();
            if (isOver) {
                mAdapter.getLoadMoreModule().loadMoreEnd();
            }
        }
    }
}
