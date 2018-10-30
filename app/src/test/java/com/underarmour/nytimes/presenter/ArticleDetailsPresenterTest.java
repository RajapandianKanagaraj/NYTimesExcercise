package com.underarmour.nytimes.ui.presenter;

import com.underarmour.nytimes.models.Article;
import com.underarmour.nytimes.models.Byline;
import com.underarmour.nytimes.models.Headline;
import com.underarmour.nytimes.models.Multimedia;
import com.underarmour.nytimes.mvp.ArticleDetailsMVPContract;
import com.underarmour.nytimes.presenter.ArticleDetailsPresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

public class ArticleDetailsPresenterTest {

    private ArticleDetailsMVPContract.ArticleDetailsPresenter detailsPresenter;

    private ArticleDetailsMVPContract.ArticleDetailsView articleDetailsView;

    @Before
    public void setup() {
        detailsPresenter = new ArticleDetailsPresenterImpl();
        articleDetailsView = Mockito.mock(ArticleDetailsMVPContract.ArticleDetailsView.class);
        detailsPresenter.setView(articleDetailsView);
    }

    @Test
    public void testArticleHasHeadLine() {
        Article article = Mockito.mock(Article.class);
        Headline headline = Mockito.mock(Headline.class);
        Mockito.when(headline.getMain()).thenReturn("Headline");
        Mockito.when(article.getHeadline()).thenReturn(headline);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.atLeastOnce()).showHeadline("Headline");
    }

    @Test
    public void testArticleHeadLineIsNull() {
        Article article = Mockito.mock(Article.class);
        Mockito.when(article.getHeadline()).thenReturn(null);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.never()).showHeadline(Mockito.anyString());
    }

    @Test
    public void testArticleHasSnippet() {
        Article article = Mockito.mock(Article.class);
        Mockito.when(article.getSnippet()).thenReturn("Snippet");
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.atLeastOnce()).showSnippet("Snippet");
    }

    @Test
    public void testArticleSnippetIsNull() {
        Article article = Mockito.mock(Article.class);
        Mockito.when(article.getSnippet()).thenReturn(null);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.never()).showSnippet(Mockito.anyString());
    }

    @Test
    public void testArticleHasSource() {
        Article article = Mockito.mock(Article.class);
        Mockito.when(article.getSource()).thenReturn("Newyork Times");
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.atLeastOnce()).showSource(Mockito.anyString());
    }

    @Test
    public void testArticleSourceIsNull() {
        Article article = Mockito.mock(Article.class);
        Mockito.when(article.getSource()).thenReturn(null);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.never()).showSource(Mockito.anyString());
    }

    @Test
    public void testArticleHasPublishedDate() {
        Article article = Mockito.mock(Article.class);
        Mockito.when(article.getPubDate()).thenReturn("2018-07-04T23:00:04+0000");
        detailsPresenter.setView(articleDetailsView);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.atLeastOnce()).showPublishedDate("Jul, 04 2018");
    }

    @Test
    public void testArticlePublishedDateIsNull() {
        Article article = Mockito.mock(Article.class);
        Mockito.when(article.getPubDate()).thenReturn(null);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.never()).showPublishedDate(Mockito.anyString());
    }

    @Test
    public void testArticleHasAuthor() {
        Article article = Mockito.mock(Article.class);
        Byline byline = Mockito.mock(Byline.class);
        Mockito.when(byline.getOriginal()).thenReturn("Someone");
        Mockito.when(article.getByline()).thenReturn(byline);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.atLeastOnce()).showAuthor("Someone");
    }

    @Test
    public void testArticleAuthorIsNull() {
        Article article = Mockito.mock(Article.class);
        Mockito.when(article.getByline()).thenReturn(null);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.never()).showAuthor(Mockito.anyString());
    }

    @Test
    public void testArticleHasImage() {
        Article article = Mockito.mock(Article.class);
        Multimedia multimedia = Mockito.mock(Multimedia.class);
        Mockito.when(multimedia.getSubType()).thenReturn("jumbo");
        Mockito.when(multimedia.getUrl()).thenReturn("images/2018/09/06/arts/06BookWoodward-Dress/merlin_142169574_b921ed94-acd2-48eb-8da2-ba810a06f1d3-articleLarge.jpg");
        ArrayList<Multimedia> multimediaList = new ArrayList<>();
        multimediaList.add(multimedia);
        Mockito.when(article.getMultimedia()).thenReturn(multimediaList);
        detailsPresenter.setView(articleDetailsView);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.atLeastOnce()).loadArticleImage(Mockito.anyString());
    }

    @Test
    public void testArticleMultimediaIsNull() {
        Article article = Mockito.mock(Article.class);
        Mockito.when(article.getMultimedia()).thenReturn(null);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.never()).loadArticleImage(Mockito.anyString());
    }

    @Test
    public void testArticleMultimediaNotHaveLargeImage() {
        Article article = Mockito.mock(Article.class);
        Multimedia multimedia = Mockito.mock(Multimedia.class);
        Mockito.when(multimedia.getSubType()).thenReturn(null);
        ArrayList<Multimedia> multimediaList = new ArrayList<>();
        multimediaList.add(multimedia);
        Mockito.when(article.getMultimedia()).thenReturn(multimediaList);
        detailsPresenter.showArticleDetails(article);

        Mockito.verify(articleDetailsView, Mockito.never()).loadArticleImage(Mockito.anyString());
    }

    @Test
    public void testFragemntShouldShowErrorMessageWhenArticleIsNull() {
        detailsPresenter.setView(articleDetailsView);
        detailsPresenter.showArticleDetails(null);

        Mockito.verify(articleDetailsView, Mockito.atLeastOnce()).showErrorMessage();
    }
}
