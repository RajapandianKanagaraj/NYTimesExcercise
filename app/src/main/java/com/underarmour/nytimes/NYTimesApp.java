package com.underarmour.nytimes;

import android.app.Application;

import com.squareup.picasso.Picasso;
import com.underarmour.nytimes.di.ContextModule;
import com.underarmour.nytimes.di.DaggerNetworkAPIComponent;
import com.underarmour.nytimes.di.NYTimesSearchAPIModule;
import com.underarmour.nytimes.di.NetworkAPIComponent;
import com.underarmour.nytimes.di.NetworkModule;
import com.underarmour.nytimes.di.PicassoModule;
import com.underarmour.nytimes.network.NYTimesSearchAPI;

public class NYTimesApp extends Application {

    private static NYTimesApp appInstance;

    public static NYTimesApp getInstance() {
        return appInstance;
    }

    private NYTimesSearchAPI nyTimesSearchAPI;
    private Picasso picasso;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;

        NetworkAPIComponent networkAPIComponent = DaggerNetworkAPIComponent.builder()
                .contextModule(new ContextModule(this))
                .networkModule(new NetworkModule())
                .nYTimesSearchAPIModule(new NYTimesSearchAPIModule())
                .picassoModule(new PicassoModule())
                .build();

        nyTimesSearchAPI = networkAPIComponent.provideNYTimesSearchService();
        picasso = networkAPIComponent.providePicasso();
    }
}
