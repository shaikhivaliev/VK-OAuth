package com.webim.testshaikhivaliev.net;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.webim.testshaikhivaliev.AppDelegate;
import com.webim.testshaikhivaliev.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getInstance());
        final String accessToken = preferences.getString("accessToken", null);

        Request request = chain.request();
        HttpUrl httpUrl = request.url().newBuilder()
                .addQueryParameter("access_token", accessToken)
                .addQueryParameter("v", BuildConfig.API_VERSION)
                .build();

        request = request.newBuilder()
                .url(httpUrl)
                .build();
        return chain.proceed(request);
    }
}
