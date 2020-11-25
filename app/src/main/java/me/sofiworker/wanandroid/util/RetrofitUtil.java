package me.sofiworker.wanandroid.util;

import me.sofiworker.wanandroid.Api;
import me.sofiworker.wanandroid.Constants;
import me.sofiworker.wanandroid.interceptor.CookieInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/3/18 22:00
 */
public class RetrofitUtil {

    public static Api getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new CookieInterceptor()).build();
        builder.baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder.build().create(Api.class);
    }
}
