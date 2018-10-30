package com.underarmour.nytimes.di;

import com.squareup.picasso.Picasso;
import com.underarmour.nytimes.network.NYTimesSearchAPI;

import dagger.Component;

@Component(modules = {NYTimesSearchAPIModule.class, PicassoModule.class})
@NYTimesAppScope
public interface NetworkAPIComponent {

    NYTimesSearchAPI provideNYTimesSearchService();

    Picasso providePicasso();
}
