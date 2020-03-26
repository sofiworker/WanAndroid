package me.sofiworker.wanandroid;

import android.app.Application;
import android.content.MutableContextWrapper;
import android.webkit.WebView;

import com.tamsiree.rxkit.RxTool;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/18 19:45
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RxTool.init(this);
        WebView mWebView = new WebView(new MutableContextWrapper(this));
    }
}
