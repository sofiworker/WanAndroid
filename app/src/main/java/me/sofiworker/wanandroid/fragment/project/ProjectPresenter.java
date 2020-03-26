package me.sofiworker.wanandroid.fragment.project;

import java.util.List;

import me.sofiworker.wanandroid.base.BaseObserver;
import me.sofiworker.wanandroid.base.BasePresenter;
import me.sofiworker.wanandroid.util.RxUtil;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/24 8:55
 */
public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter {


    @Override
    public void loadProjectTitle() {
        addDisposable(mApi.getProjectList()
                .compose(RxUtil.changeRxThread()).subscribeWith(new BaseObserver<List<Project>>() {
            @Override
            protected void onSuccess(List<Project> data) {
                if (!data.isEmpty()) {
                    mView.showProjectTitleList(data);
                }
            }
        }));
    }
}
