package com.underarmour.nytimes.ui.activity;

import com.underarmour.nytimes.BuildConfig;
import com.underarmour.nytimes.ui.fragment.ArticleDetailsFragment;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ArticleDetailsActivityTest {

    private ArticleDetailsActivity articleDetailsActivity;

    @Before
    public void setup() {
        articleDetailsActivity = Robolectric.buildActivity(ArticleDetailsActivity.class)
                .create()
                .visible()
                .get();
    }

    @Test
    public void testActivityShouldNotBeNull() {
        Assert.assertNotNull(articleDetailsActivity);
    }


    @Test
    public void testActivityShouldHaveSearchListFragment() {
        Assert.assertNotNull(articleDetailsActivity.getSupportFragmentManager()
                .findFragmentByTag(ArticleDetailsFragment.TAG));
    }
}
