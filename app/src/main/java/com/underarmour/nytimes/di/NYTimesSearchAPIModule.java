package com.underarmour.nytimes.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.underarmour.nytimes.network.APIConstants;
import com.underarmour.nytimes.network.NYTimesSearchAPI;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {NetworkModule.class})
public class NYTimesSearchAPIModule {

    @Provides
    @NYTimesAppScope
    public NYTimesSearchAPI getNYTimesSearchAPI(Retrofit retrofit) {
        return retrofit.create(NYTimesSearchAPI.class);
    }

    @Provides
    @NYTimesAppScope
    public Retrofit getRetrofit(OkHttpClient okHttpClient, Gson gson) {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(APIConstants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
}
