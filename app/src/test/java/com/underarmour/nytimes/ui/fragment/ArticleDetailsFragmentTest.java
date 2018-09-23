package com.underarmour.nytimes.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class ArticleDetailsFragmentTest {

    private ArticleDetailsActivity articleDetailsActivity;
    private ArticleDetailsFragment articleDetailsFragment;
    private View rootView;
    private ArticleDetailsMVPContract.ArticleDetailsView articleDetailsView;

    @Before
    public void setup() {
        articleDetailsActivity = Robolectric.buildActivity(ArticleDetailsActivity.class)
                .create()
                .visible()
                .get();
        articleDetailsFragment = new ArticleDetailsFragment();
        SupportFragmentTestUtil.startFragment(articleDetailsFragment);
        rootView = articleDetailsFragment.getView();

        articleDetailsView = Mockito.mock(ArticleDetailsMVPContract.ArticleDetailsView.class);
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
    public void testFragmentShouldShowHeadLine() {
        Article article = Mockito.mock(Article.class);
        Headline headline = Mockito.mock(Headline.class);
        Mockito.when(headline.getMain()).thenReturn("Headline");
        Mockito.when(article.getHeadline()).thenReturn(headline);
        ArticleDetailsMVPContract.ArticleDetailsPresenter detailsPresenter = new ArticleDetailsPresenterImpl();
        detailsPresenter.setView(articleDetailsView);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.atLeastOnce()).showHeadline("Headline");
//
//        TextView headLineTextView = rootView.findViewById(R.id.textview_headline);
//        Assert.assertTrue("TextView contains incorrect text",
//                "Headline".equals(headLineTextView.getText().toString()));
    }

    @Test
    public void testFragmentShouldShowArticleSnippet() {
        Article article = Mockito.mock(Article.class);
        Mockito.when(article.getSnippet()).thenReturn("Snippet");
        ArticleDetailsMVPContract.ArticleDetailsPresenter detailsPresenter = new ArticleDetailsPresenterImpl();
        detailsPresenter.setView(articleDetailsView);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.atLeastOnce()).showSnippet("Snippet");
    }

    @Test
    public void testFragmentShouldShowArticleSource() {
        Article article = Mockito.mock(Article.class);
        Mockito.when(article.getSource()).thenReturn("Newyork Times");
        ArticleDetailsMVPContract.ArticleDetailsPresenter detailsPresenter = new ArticleDetailsPresenterImpl();
        detailsPresenter.setView(articleDetailsView);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.atLeastOnce()).showSource(Mockito.anyString());
    }

    @Test
    public void testFragmentShouldShowArticlePublishedDate() {
        Article article = Mockito.mock(Article.class);
        Mockito.when(article.getPubDate()).thenReturn("2018-07-04T23:00:04+0000");
        ArticleDetailsMVPContract.ArticleDetailsPresenter detailsPresenter = new ArticleDetailsPresenterImpl();
        detailsPresenter.setView(articleDetailsView);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.atLeastOnce()).showPublishedDate("Jul, 04 2018");
    }

    @Test
    public void testFragmentShouldShowArticleAuthor() {
        Article article = Mockito.mock(Article.class);
        Byline byline = Mockito.mock(Byline.class);
        Mockito.when(byline.getOriginal()).thenReturn("Someone");
        Mockito.when(article.getByline()).thenReturn(byline);
        ArticleDetailsMVPContract.ArticleDetailsPresenter detailsPresenter = new ArticleDetailsPresenterImpl();
        detailsPresenter.setView(articleDetailsView);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.atLeastOnce()).showAuthor("Someone");
    }

    @Test
    public void testFragmentShouldShowArticleImage() {
        Article article = Mockito.mock(Article.class);
        Multimedia multimedia = Mockito.mock(Multimedia.class);
        Mockito.when(multimedia.getSubType()).thenReturn("jumbo");
        Mockito.when(multimedia.getUrl()).thenReturn("images/2018/09/06/arts/06BookWoodward-Dress/merlin_142169574_b921ed94-acd2-48eb-8da2-ba810a06f1d3-articleLarge.jpg");
        ArrayList<Multimedia> multimediaList = new ArrayList<>();
        multimediaList.add(multimedia);
        Mockito.when(article.getMultimedia()).thenReturn(multimediaList);
        ArticleDetailsMVPContract.ArticleDetailsPresenter detailsPresenter = new ArticleDetailsPresenterImpl();
        detailsPresenter.setView(articleDetailsView);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.atLeastOnce()).loadArticleImage(Mockito.anyString());
    }

    @Test
    public void testFragemntShouldShowErrorMessageWhenArticleIsNull() {
        ArticleDetailsMVPContract.ArticleDetailsPresenter detailsPresenter = new ArticleDetailsPresenterImpl();
        detailsPresenter.setView(articleDetailsView);
        detailsPresenter.showArticleDetails(null);

        Mockito.verify(articleDetailsView, Mockito.atLeastOnce()).showErrorMessage(Mockito.anyString());
    }
}
