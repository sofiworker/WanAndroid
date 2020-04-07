package me.sofiworker.wanandroid.fragment.search;

import android.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.activity.search.SearchActivity;
import me.sofiworker.wanandroid.adapter.TagRvAdapter;
import me.sofiworker.wanandroid.base.BaseFragment;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/26 14:47
 */
public class SearchFragment extends BaseFragment<SearchPresenter> implements SearchContract.View {

    @BindView(R.id.rv_hot_key)
    RecyclerView mHotKeyRv;
    @BindView(R.id.rv_search_history)
    RecyclerView mSearchHistoryRv;
    @BindView(R.id.tv_clear_all)
    TextView mClearAllTv;
    private TagRvAdapter mHotTagAdapter;
    private TagRvAdapter mHistoryTagAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initThings() {
        mPresenter = new SearchPresenter();
        mPresenter.attachView(this);

        initRv();

        mPresenter.loadHotSearch();
        mPresenter.loadHistory();
    }

    @OnClick(R.id.tv_clear_all)
    void onClick() {
        if (getActivity() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                    .setTitle(getActivity().getApplicationContext().getString(R.string.prompt))
                    .setMessage(getActivity().getString(R.string.clear_all_history))
                    .setPositiveButton(getActivity().getString(R.string.confirm), (dialog, which) -> {
                mPresenter.clearAllHistory();
                mPresenter.loadHistory();
            })
                .setNegativeButton(getActivity().getString(R.string.cancel), null);
            builder.create().show();
        }
    }

    private void initRv() {
        FlexboxLayoutManager hotKeyLayoutManager = new FlexboxLayoutManager(getContext());
        hotKeyLayoutManager.setFlexDirection(FlexDirection.ROW);
        hotKeyLayoutManager.setFlexWrap(FlexWrap.WRAP);
        mHotKeyRv.setLayoutManager(hotKeyLayoutManager);
        mHotTagAdapter = new TagRvAdapter(R.layout.rv_item_tag);
        mHotKeyRv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mHotKeyRv.setAdapter(mHotTagAdapter);
        mHotTagAdapter.setClickEvent(str -> {
            ((SearchActivity) Objects.requireNonNull(getActivity())).showResult(str, true);
            saveOrChangeHistory(str);
        });

        FlexboxLayoutManager historyLayoutManager = new FlexboxLayoutManager(getContext());
        historyLayoutManager.setFlexDirection(FlexDirection.ROW);
        historyLayoutManager.setFlexWrap(FlexWrap.WRAP);
        mSearchHistoryRv.setLayoutManager(historyLayoutManager);
        mHistoryTagAdapter = new TagRvAdapter(R.layout.rv_item_tag);
        mSearchHistoryRv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mSearchHistoryRv.setAdapter(mHistoryTagAdapter);
        mHistoryTagAdapter.setClickEvent(str -> {
            ((SearchActivity) Objects.requireNonNull(getActivity())).showResult(str, true);
            saveOrChangeHistory(str);
        });
        mHistoryTagAdapter.setLongClickEvent(str -> {

        });
    }

    public void saveOrChangeHistory(String searchStr) {
        int index = mHistoryTagAdapter.getData().indexOf(searchStr);
        if (index == -1) {
            mHistoryTagAdapter.addData(0, searchStr);
        }else {
            String str = mHistoryTagAdapter.getItem(index);
            mHistoryTagAdapter.remove(index);
            mHistoryTagAdapter.addData(0, str);
        }
        mPresenter.saveHistory(mHistoryTagAdapter.getData());
    }

    @Override
    public void showHotSearch(List<String> stringList) {
        mHotTagAdapter.setNewData(stringList);
    }

    @Override
    public void showHistory(List<String> stringList, boolean isEmpty) {
        if (isEmpty) {
            mClearAllTv.setVisibility(View.GONE);
        }else {
            mClearAllTv.setVisibility(View.VISIBLE);
            mHistoryTagAdapter.setNewData(stringList);
        }
    }
}
