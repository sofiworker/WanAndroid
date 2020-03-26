package me.sofiworker.wanandroid.fragment.home;

import com.tamsiree.rxkit.RxTool;

import java.util.List;

import io.reactivex.Observable;
import me.sofiworker.wanandroid.R;
import me.sofiworker.wanandroid.base.BaseData;
import me.sofiworker.wanandroid.base.BaseObserver;
import me.sofiworker.wanandroid.base.BasePresenter;
import me.sofiworker.wanandroid.util.RxUtil;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/19 14:13
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private static int pageNum = 0;

    @Override
    public void loadBanner() {
        addDisposable(mApi.getBanner().compose(RxUtil.changeRxThread()).subscribeWith(new BaseObserver<List<BannerData>>(mView) {
            @Override
            protected void onSuccess(List<BannerData> data) {
                if (data.isEmpty()) {
                    mView.showError(RxTool.getContext().getString(R.string.error));
                } else {
                    mView.showBanner(data);
                }
            }
        }));
    }

    @Override
    public void loadFirstArticlePage() {
        addDisposable(Observable.zip(mApi.getTopArticleList(), mApi.getArticleListByPage(0),
                (listBaseData, pageBaseData) -> {
                    BaseData<List<Article>> baseData = new BaseData<>();
                    if (listBaseData.getErrorCode() == 0 && pageBaseData.getErrorCode() == 0
                            && listBaseData.getData() != null && pageBaseData.getData().getDatas() != null) {
                        List<Article> articleList = listBaseData.getData();
                        ArticlePage articlePage = pageBaseData.getData();
                        List<Article> pageData = articlePage.getDatas();
                        baseData.setErrorCode(0);
                        baseData.setErrorMsg("");
                        boolean isSuccess = articleList.addAll(pageData);
                        if (isSuccess) {
                            baseData.setData(articleList);
                        }
                    }
                    return baseData;
                })
                .compose(RxUtil.changeRxThread()).subscribeWith(new BaseObserver<List<Article>>() {
            @Override
            protected void onSuccess(List<Article> data) {
                if (data == null) {
                    mView.showError(RxTool.getContext().getString(R.string.error));
                }else {
                    mView.showFirstArticlePage(data);
                }
            }
        }));
    }

    @Override
    public void loadMoreArticle() {
        addDisposable(mApi.getArticleListByPage(++pageNum)
                .compose(RxUtil.changeRxThread()).subscribeWith(new BaseObserver<ArticlePage>() {
                    @Override
                    protected void onSuccess(ArticlePage data) {
                        if (data.getDatas() != null) {
                            mView.showMoreArticle(data.getDatas(), data.isOver());
                        }
                    }
                }));
    }
}
