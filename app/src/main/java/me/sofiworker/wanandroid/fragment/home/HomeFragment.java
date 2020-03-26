package me.sofiworker.wanandroid.fragment.home;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.activity.main.MainActivity;
import me.sofiworker.wanandroid.activity.search.SearchActivity;
import me.sofiworker.wanandroid.activity.web.WebActivity;
import me.sofiworker.wanandroid.adapter.CommonRvAdapter;
import me.sofiworker.wanandroid.base.BaseFragment;
import me.sofiworker.wanandroid.util.ActivityUtil;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/19 10:30
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.refresh_home)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.banner_home)
    Banner mBanner;
    @BindView(R.id.rv_home)
    RecyclerView mHomeRv;
    @BindView(R.id.iv_search)
    ImageView mSearchIv;

    private CommonRvAdapter mAdapter;

    private List<BannerData> mBannerDataList;

    private List<Article> mArticles = new ArrayList<>();

    @OnClick(R.id.iv_search)
    void click() {
        startActivity(new Intent(getContext(), SearchActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initThings() {
        mPresenter = new HomePresenter();
        mPresenter.attachView(this);

        initRv();
        loadData();
    }

    private void loadData() {
        mPresenter.loadFirstArticlePage();
        mPresenter.loadBanner();
        loadMoreArticle();
    }

    private void loadMoreArticle() {
        if (mAdapter.getLoadMoreModule() != null) {
            mAdapter.getLoadMoreModule().setOnLoadMoreListener(() -> {
                mPresenter.loadMoreArticle();
            });
        }
    }

    private void initRv(){
        mHomeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CommonRvAdapter(R.layout.rv_item_common);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            ActivityUtil.goToArticleWeb(getContext(), mArticles.get(position));
        });
        mHomeRv.setAdapter(mAdapter);
        createBanner();
    }

    private void createBanner() {
        if (mBanner != null && getContext() != null) {
            ((LinearLayout)mRootView).removeView(mBanner);
            int height = (int) (getContext().getResources().getDisplayMetrics().widthPixels * (9F / 16F));
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
            mBanner.setLayoutParams(layoutParams);
            mBanner.setImageLoader(new BannerImageLoader());
            mBanner.setDelayTime(1500);
            mBanner.setOnBannerListener(position -> {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("banner", mBannerDataList.get(position));
                startActivity(intent);
            });
            mBanner.startAutoPlay();
            mAdapter.addHeaderView(mBanner, 0);
        }
    }

    @Override
    public void showBanner(List<BannerData> bannerDataList) {
        mBannerDataList = bannerDataList;
        List<String> imageList = new ArrayList<>();
        for (BannerData bannerData : mBannerDataList) {
            imageList.add(bannerData.getImagePath());
        }
        mBanner.setImages(imageList).start();
    }

    @Override
    public void showFirstArticlePage(List<Article> articles) {
        mArticles.addAll(articles);
        mAdapter.setNewData(articles);
    }

    @Override
    public void showMoreArticle(List<Article> articles, boolean isOver) {
        mAdapter.addData(articles);
        mArticles.addAll(articles);
        if (mAdapter.getLoadMoreModule() != null) {
            mAdapter.getLoadMoreModule().loadMoreComplete();
            if (isOver) {
                mAdapter.getLoadMoreModule().loadMoreEnd();
            }
        }
    }
}
