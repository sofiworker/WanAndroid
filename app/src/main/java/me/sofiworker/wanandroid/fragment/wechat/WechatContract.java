package me.sofiworker.wanandroid.fragment.wechat;

import java.util.List;

import me.sofiworker.wanandroid.base.IPresenter;
import me.sofiworker.wanandroid.base.IView;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/21 19:41
 */
public interface WechatContract {

    interface View extends IView {
        void showDataWithMi(List<Wechat> wechatList);
    }

    interface Presenter extends IPresenter<View> {
        void loadWechatData();
    }
}
