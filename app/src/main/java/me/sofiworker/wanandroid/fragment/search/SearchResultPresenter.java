package me.sofiworker.wanandroid.fragment.search;

import me.sofiworker.wanandroid.base.BaseObserver;
import me.sofiworker.wanandroid.base.BasePresenter;
import me.sofiworker.wanandroid.fragment.home.ArticlePage;
import me.sofiworker.wanandroid.util.RxUtil;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/29 22:41
 */
public class SearchResultPresenter extends BasePresenter<SearchResultContract.View> implements SearchResultContract.Presenter {

    private int pageNum;

    @Override
    public void loadResult(String str, boolean isNew) {
        if (isNew) {
            pageNum = 0;
        }
        addDisposable(mApi.searchArticle(pageNum++, str).compose(RxUtil.changeRxThread())
        .subscribeWith(new BaseObserver<ArticlePage>() {
            @Override
            protected void onSuccess(ArticlePage data) {
                if (data != null) {
                    mView.showArticleList(data.getDatas(), data.isOver(), isNew);
                }
            }
        }));
    }
}
