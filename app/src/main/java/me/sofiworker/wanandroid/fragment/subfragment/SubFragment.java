package me.sofiworker.wanandroid.fragment.subfragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.adapter.CommonRvAdapter;
import me.sofiworker.wanandroid.base.BaseFragment;
import me.sofiworker.wanandroid.fragment.home.Article;
import me.sofiworker.wanandroid.util.ActivityUtil;
import me.sofiworker.wanandroid.util.LogUtil;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/21 20:05
 */
public class SubFragment extends BaseFragment<SubPresenter> implements SubContract.View {

    @BindView(R.id.rv_common_article)
    RecyclerView mCommonRv;
    private CommonRvAdapter mAdapter;

    private List<Article> mArticleList = new ArrayList<>();
    private int id;
    private Type type;

    public enum Type {
        /**
         * 加载数据类型
         */
        wechat,
        project,
        subsystem
    }

    public SubFragment(int id, Type type) {
        this.id = id;
        this.type = type;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rv_common_article_list;
    }

    @Override
    protected void initThings() {
        mPresenter = new SubPresenter();
        mPresenter.attachView(this);

        initRv();
        loadData();
    }

    private void loadData() {
        switch (type) {
            case wechat:
                mPresenter.loadWechatArticleList(id);
                break;
            case project:
                mPresenter.loadProjectArticleList(id);
                break;
            case subsystem:
                mPresenter.loadSubsystemArticleList(id);
                break;
            default:
                break;
        }
    }

    private void initRv() {
        mCommonRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CommonRvAdapter(R.layout.rv_item_common);
        if (mAdapter.getLoadMoreModule() != null) {
            mAdapter.getLoadMoreModule().setOnLoadMoreListener(() -> {
                if (type == Type.wechat) {
                    mPresenter.loadWechatArticleList(id);
                } else if (type == Type.project){
                    mPresenter.loadProjectArticleList(id);
                }else if (type == Type.subsystem){
                    mPresenter.loadSubsystemArticleList(id);
                }
            });
        }
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            ActivityUtil.goToArticleWeb(getContext(), mArticleList.get(position));
        });
        mCommonRv.setAdapter(mAdapter);
    }

    @Override
    public void showArticleList(List<Article> articles, boolean isOver) {
        mArticleList.addAll(articles);
        mAdapter.addData(articles);
        if (mAdapter.getLoadMoreModule() != null) {
            mAdapter.getLoadMoreModule().loadMoreComplete();
            if (isOver) {
                mAdapter.getLoadMoreModule().loadMoreEnd();
            }
        }
    }
}
