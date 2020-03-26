package me.sofiworker.wanandroid.util;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/19 15:11
 */
public class RxUtil {

    public static <T> ObservableTransformer<T, T> changeRxThread(){
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
