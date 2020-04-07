package me.sofiworker.wanandroid.util;

import android.content.Context;
import android.content.SharedPreferences;

import me.sofiworker.wanandroid.App;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/4/7 21:10
 */
public class SpUtil {

    public static void saveSearchHistory(String content){
        SharedPreferences.Editor edit = App.getContext().getSharedPreferences("search_history", Context.MODE_PRIVATE).edit();
        edit.putString("search_history", content).apply();
    }

    public static void clearAllSearchHistory(){
        SharedPreferences.Editor edit = App.getContext().getSharedPreferences("search_history", Context.MODE_PRIVATE).edit();
        edit.clear().apply();
    }

    public static String getSearchHistory() {
        SharedPreferences sp = App.getContext().getSharedPreferences("search_history", Context.MODE_PRIVATE);
        return sp.getString("search_history", "");
    }
}
