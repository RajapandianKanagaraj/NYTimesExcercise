package com.underarmour.nytimes.di;

import android.content.Context;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.underarmour.nytimes.NYTimesApp;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module (includes = {NetworkModule.class, ContextModule.class})
public class PicassoModule {

    @Provides
    @NYTimesAppScope
    OkHttp3Downloader getOkHttp3Downloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }

    @Provides
    @NYTimesAppScope
    Picasso getPicasso(Context context, OkHttp3Downloader okHttp3Downloader) {
        Picasso.Builder builder = new Picasso.Builder(context);
        Picasso picasso = builder
                .downloader(okHttp3Downloader)
                .build();
        try {
            Picasso.setSingletonInstance(picasso);
        } catch (IllegalStateException ignored) {
        }
        return picasso;
    }
}
