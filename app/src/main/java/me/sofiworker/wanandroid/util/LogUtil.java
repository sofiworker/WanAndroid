package me.sofiworker.wanandroid.util;

import android.util.Log;

import java.sql.Timestamp;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/19 9:00
 */
public final class LogUtil {

    private static final String TAG = "LogUtil";

    public static void d(String msg){
        Log.d(new Timestamp(System.currentTimeMillis()).toString()+"----"+TAG, "===========>"+msg);
    }
}
