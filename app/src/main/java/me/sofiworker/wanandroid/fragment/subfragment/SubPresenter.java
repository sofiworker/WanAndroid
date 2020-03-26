package me.sofiworker.wanandroid.fragment.subfragment;

import java.util.List;

import me.sofiworker.wanandroid.base.BaseObserver;
import me.sofiworker.wanandroid.base.BasePresenter;
import me.sofiworker.wanandroid.fragment.home.ArticlePage;
import me.sofiworker.wanandroid.util.RxUtil;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/21 20:08
 */
public class SubPresenter extends BasePresenter<SubContract.View> implements SubContract.Presenter {

    private int pageNum = 0;

    @Override
    public void loadWechatArticleList(int wechatId) {
        addDisposable(mApi.getWechatArticle(wechatId, ++pageNum)
                .compose(RxUtil.changeRxThread()).subscribeWith(new BaseObserver<ArticlePage>() {
                    @Override
                    protected void onSuccess(ArticlePage data) {
                        mView.showArticleList(data.getDatas(), data.isOver());
                    }
                }));
    }

    @Override
    public void loadProjectArticleList(int projectId) {
        addDisposable(mApi.getProjectArticleList(++pageNum, projectId)
                .compose(RxUtil.changeRxThread()).subscribeWith(new BaseObserver<ArticlePage>() {
                    @Override
                    protected void onSuccess(ArticlePage data) {
                        mView.showArticleList(data.getDatas(), data.isOver());
                    }
                }));
    }

    @Override
    public void loadSubsystemArticleList(int subsystemId) {
        addDisposable(mApi.getSubsystemArticleList(pageNum++, subsystemId).compose(RxUtil.changeRxThread())
                .subscribeWith(new BaseObserver<ArticlePage>() {
                    @Override
                    protected void onSuccess(ArticlePage data) {
                        mView.showArticleList(data.getDatas(), data.isOver());
                    }
                }));
    }
}
