package com.underarmour.nytimes.ui.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.underarmour.nytimes.BuildConfig;
import com.underarmour.nytimes.R;
import com.underarmour.nytimes.ui.activity.HomeSearchActivity;
import com.underarmour.nytimes.ui.fragment.ArticleListFragment;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class HomeSearchActivityTest {

    private HomeSearchActivity homeSearchActivity;

    @Before
    public void setup() {
        homeSearchActivity = Robolectric.buildActivity(HomeSearchActivity.class)
                .create()
                .visible()
                .get();
    }

    @Test
    public void testActivityShouldNotBeNull() {
        Assert.assertNotNull(homeSearchActivity);
    }

    @Test
    public void testActivityShouldHaveToolBar() {
        ActionBar toolbar = homeSearchActivity.getSupportActionBar();
        Assert.assertNotNull(toolbar);
    }

    @Test
    public void testActivityToolBarShouldHaveSearchMenuItem() {
        Menu menu = Shadows.shadowOf(homeSearchActivity).getOptionsMenu();
        MenuItem searchBtn = menu.findItem(R.id.action_search);
        Assert.assertNotNull(searchBtn);
    }

    @Test
    public void testActivityShouldHaveSearchListFragment() {
        Assert.assertNotNull(homeSearchActivity.getSupportFragmentManager()
                .findFragmentByTag(ArticleListFragment.TAG));
    }
}
