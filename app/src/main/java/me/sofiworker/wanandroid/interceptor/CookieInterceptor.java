package me.sofiworker.wanandroid.interceptor;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author sofiworker
 * @version 1.0.0
 * @date 2020/4/28 9:44
 */
public class CookieInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        if (!response.headers("Set-Cookie").isEmpty()) {
            List<String> headers = response.headers("Set-Cookie");
//            Observable.create()
        }

        return response;
    }
}
