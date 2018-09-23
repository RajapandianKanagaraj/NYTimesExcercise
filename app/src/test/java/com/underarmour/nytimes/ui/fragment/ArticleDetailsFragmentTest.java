package com.underarmour.nytimes.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.underarmour.nytimes.BuildConfig;
import com.underarmour.nytimes.R;
import com.underarmour.nytimes.models.Article;
import com.underarmour.nytimes.models.Byline;
import com.underarmour.nytimes.models.Headline;
import com.underarmour.nytimes.models.Multimedia;
import com.underarmour.nytimes.mvp.ArticleDetailsMVPContract;
import com.underarmour.nytimes.presenter.ArticleDetailsPresenterImpl;
import com.underarmour.nytimes.ui.activity.ArticleDetailsActivity;
import com.underarmour.nytimes.utils.AppConstants;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class ArticleDetailsFragmentTest {

    private ArticleDetailsActivity articleDetailsActivity;
    private ArticleDetailsFragment articleDetailsFragment;
    private View rootView;
    private ArticleDetailsMVPContract.ArticleDetailsView articleDetailsView;
    private ArticleDetailsMVPContract.ArticleDetailsPresenter detailsPresenter;

    @Before
    public void setup() {

        Article article = Mockito.mock(Article.class);
        Headline headline = Mockito.mock(Headline.class);
        Mockito.when(headline.getMain()).thenReturn("Headline");
        Mockito.when(article.getHeadline()).thenReturn(headline);

        Mockito.when(article.getSnippet()).thenReturn("Snippet");
        Mockito.when(article.getSource()).thenReturn("Newyork Times");
        Mockito.when(article.getPubDate()).thenReturn("2018-07-04T23:00:04+0000");

        Byline byline = Mockito.mock(Byline.class);
        Mockito.when(byline.getOriginal()).thenReturn("Someone");
        Mockito.when(article.getByline()).thenReturn(byline);

        Mockito.when(article.getWebUrl()).thenReturn("https://www.nytimes.com/2018/09/05/books/review-fear-trump-in-white-house-bob-woodward.html");


        articleDetailsActivity = Robolectric.buildActivity(ArticleDetailsActivity.class)
                .create()
                .visible()
                .get();
        articleDetailsFragment = new ArticleDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.BUNDLE_KEY_ARTICLE, article);
        articleDetailsFragment.setArguments(bundle);
        SupportFragmentTestUtil.startFragment(articleDetailsFragment);
        rootView = articleDetailsFragment.getView();

        articleDetailsView = articleDetailsFragment;
        articleDetailsView.setRootView(rootView);

        detailsPresenter = Mockito.mock(ArticleDetailsPresenterImpl.class);
        detailsPresenter.setView(articleDetailsView);
        articleDetailsView.setPresenter(detailsPresenter);
    }

    @Test
    public void testFragmentViewsShouldNotBeNull() {
        TextView headLineTextView = rootView.findViewById(R.id.textview_headline);
        TextView snippetTextView = rootView.findViewById(R.id.textview_snippet);
        TextView dateTextView = rootView.findViewById(R.id.textview_date);
        TextView authorTextView = rootView.findViewById(R.id.textview_author);
        TextView sourceTextView = rootView.findViewById(R.id.textview_source);
        ImageView articleImage = rootView.findViewById(R.id.imageview_article);
        Button readFullArticle = rootView.findViewById(R.id.button_read_full_article);

        Assert.assertNotNull(headLineTextView);
        Assert.assertNotNull(snippetTextView);
        Assert.assertNotNull(dateTextView);
        Assert.assertNotNull(authorTextView);
        Assert.assertNotNull(sourceTextView);
        Assert.assertNotNull(articleImage);
        Assert.assertNotNull(readFullArticle);
    }

    @Test
    public void testViewShouldShowAllArticleDetails() {
        TextView headLineTextView = rootView.findViewById(R.id.textview_headline);
        Assert.assertTrue("TextView contains incorrect text",
                "Headline".equals(headLineTextView.getText().toString()));

        TextView snippetTextView = rootView.findViewById(R.id.textview_snippet);
        Assert.assertTrue("TextView contains incorrect text",
                "Snippet".equals(snippetTextView.getText().toString()));

        TextView sourceTextView = rootView.findViewById(R.id.textview_source);
        Assert.assertTrue("TextView contains incorrect text",
                sourceTextView.getText().toString().contains("Newyork Times"));

        TextView dateTextView = rootView.findViewById(R.id.textview_date);
        Assert.assertTrue("TextView contains incorrect text",
                dateTextView.getText().toString().contains("Jul, 04 2018"));

        TextView authorTextView = rootView.findViewById(R.id.textview_author);
        Assert.assertTrue("TextView contains incorrect text",
                authorTextView.getText().toString().contains("Someone"));

    }

    @Test
    public void textViewShouldShowErrorMessageWhenArticleIsNull() {
        articleDetailsActivity = Robolectric.buildActivity(ArticleDetailsActivity.class)
                .create()
                .visible()
                .get();
        articleDetailsFragment = new ArticleDetailsFragment();
        SupportFragmentTestUtil.startFragment(articleDetailsFragment);
        rootView = articleDetailsFragment.getView();
        articleDetailsView.setRootView(rootView);

        TextView noDetailsErrorView = rootView.findViewById(R.id.tview_no_details);
        ScrollView mainView = rootView.findViewById(R.id.main_view);

        Assert.assertEquals(mainView.getVisibility(), View.GONE);
        Assert.assertEquals(noDetailsErrorView.getVisibility(), View.VISIBLE);
    }

//    @Test
//    public void testReadFullArticleBtnClick() {
//        Button readFullArticle = rootView.findViewById(R.id.button_read_full_article);
//        readFullArticle.performClick();
//
//        ShadowActivity shadowActivity = Shadows.shadowOf(articleDetailsActivity);
//        Intent startedIntent = shadowActivity.getNextStartedActivity();
//        ShadowIntent shadowIntent = Shadows.shadowOf(startedIntent);
//        junit.framework.Assert.assertEquals(shadowIntent.getIntentClass(), Intent.ACTION_VIEW);
//
//    }
}
