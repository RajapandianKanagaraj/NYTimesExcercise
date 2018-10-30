package com.underarmour.nytimes.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.underarmour.nytimes.NYTimesApp;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public enum NYTimesAPIAdapter {

    INSTANCE;

    private NYTimesSearchAPI nyTimesSearchAPI;

    NYTimesAPIAdapter() {
        nyTimesSearchAPI = getRetrofit().create(NYTimesSearchAPI.class);
        getPicasso();
    }

    public NYTimesSearchAPI getNyTimesSearchAPI() {
        return nyTimesSearchAPI;
    }

    private Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(APIConstants.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();
        return retrofit;
    }

    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson;
    }

    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    private Interceptor getNetworkInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter(APIConstants.API_KEY_QUERY, APIConstants.API_KEY)
                        .build();

                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(getHttpLoggingInterceptor())
                .addNetworkInterceptor(getNetworkInterceptor())
                .build();
        return httpClient;
    }

    private void getPicasso() {
        OkHttp3Downloader okHttp3Downloader = new OkHttp3Downloader(getOkHttpClient());
        Picasso.Builder builder = new Picasso.Builder(NYTimesApp.getInstance());
        Picasso picasso = builder
                .downloader(okHttp3Downloader)
                .build();
        try {
            Picasso.setSingletonInstance(picasso);
        } catch (IllegalStateException ignored) {
        }
    }

}
