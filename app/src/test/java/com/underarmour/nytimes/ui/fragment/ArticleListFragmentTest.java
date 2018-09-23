package com.underarmour.nytimes.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.underarmour.nytimes.BuildConfig;
import com.underarmour.nytimes.R;
import com.underarmour.nytimes.mvp.SearchMVPContract;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class ArticleListFragmentTest {

    private View rootView;

    @Mock
    private SearchMVPContract.SearchPresenter presenter;

    @Before
    public void setup() {
        ArticleListFragment articleListFragment = new ArticleListFragment();
        SupportFragmentTestUtil.startFragment(articleListFragment);
        rootView = articleListFragment.getView();
    }

    @Test
    public void testFragmentViewsShouldNotBeNull() {
        TextView searchResultsTextView = rootView.findViewById(R.id.text_search_query);
        TextView noArticlesErrorTextView = rootView.findViewById(R.id.textview_no_articles);
        TextView noNetworkErrorTextView = rootView.findViewById(R.id.no_network_error_view);
        RecyclerView articleListView = rootView.findViewById(R.id.article_list_view);

        Assert.assertNotNull(searchResultsTextView);
        Assert.assertNotNull(noArticlesErrorTextView);
        Assert.assertNotNull(noNetworkErrorTextView);
        Assert.assertNotNull(articleListView);
    }


}
