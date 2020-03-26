package me.sofiworker.wanandroid.fragment.system.knowledge;

import java.util.List;

import me.sofiworker.wanandroid.base.BaseObserver;
import me.sofiworker.wanandroid.base.BasePresenter;
import me.sofiworker.wanandroid.util.RxUtil;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/20 11:45
 */
public class KnowledgePresenter extends BasePresenter<KnowledgeContract.View> implements KnowledgeContract.Presenter {


    @Override
    public void loadKnowledgeData() {
        addDisposable(mApi.getKnowledge().compose(RxUtil.changeRxThread()).subscribeWith(new BaseObserver<List<Knowledge>>() {
            @Override
            protected void onSuccess(List<Knowledge> data) {
                mView.showKnowledgeData(data);
            }
        }));
    }
}
