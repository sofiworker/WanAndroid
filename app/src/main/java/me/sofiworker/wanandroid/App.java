package me.sofiworker.wanandroid;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.MutableContextWrapper;
import android.webkit.WebView;

import com.tamsiree.rxkit.RxTool;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/18 19:45
 */
public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        RxTool.init(this);
        WebView mWebView = new WebView(new MutableContextWrapper(this));
    }

    public static Context getContext(){
        return mContext;
    }
}
