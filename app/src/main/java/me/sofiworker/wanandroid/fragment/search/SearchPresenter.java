package me.sofiworker.wanandroid.fragment.search;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tamsiree.rxkit.RxSPTool;
import com.tamsiree.rxkit.RxTool;

import java.util.ArrayList;
import java.util.List;

import me.sofiworker.wanandroid.base.BaseObserver;
import me.sofiworker.wanandroid.base.BasePresenter;
import me.sofiworker.wanandroid.util.RxUtil;
import me.sofiworker.wanandroid.util.SpUtil;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/29 21:34
 */
public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {

    @Override
    public void loadHotSearch() {
        addDisposable(mApi.getHotKeyList()
                .compose(RxUtil.changeRxThread()).subscribeWith(new BaseObserver<List<HotKey>>() {
            @Override
            protected void onSuccess(List<HotKey> data) {
                if (data != null) {
                    List<String> stringList = new ArrayList<>();
                    for (HotKey key : data) {
                        stringList.add(key.getName());
                    }
                    mView.showHotSearch(stringList);
                }
            }
        }));
    }

    @Override
    public void loadHistory() {
        String searchHistory = SpUtil.getSearchHistory();
        if (!TextUtils.isEmpty(searchHistory)) {
            List<String> list = new Gson().fromJson(searchHistory, new TypeToken<List<String>>(){}.getType());
            mView.showHistory(list, false);
        }else {
            mView.showHistory(null, true);
        }
    }

    @Override
    public void saveHistory(List<String> stringList) {
        SpUtil.saveSearchHistory(new Gson().toJson(stringList));
    }

    @Override
    public void clearAllHistory() {
        SpUtil.clearAllSearchHistory();
    }
}
