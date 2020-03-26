package me.sofiworker.wanandroid.util;

import android.content.Context;
import android.content.Intent;

import me.sofiworker.wanandroid.activity.web.WebActivity;
import me.sofiworker.wanandroid.fragment.home.Article;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/26 14:01
 */
public class ActivityUtil {

    public static <T> void goToArticleWeb(Context context, Article article) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("article", article);
        context.startActivity(intent);
    }
}
