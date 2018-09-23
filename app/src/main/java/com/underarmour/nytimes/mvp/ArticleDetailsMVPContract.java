package com.underarmour.nytimes.mvp;

import com.underarmour.nytimes.models.Article;
import com.underarmour.nytimes.mvp.base.BasePresenter;
import com.underarmour.nytimes.mvp.base.BaseView;

public interface ArticleDetailsMVPContract {

    interface ArticleDetailsView extends BaseView {

        void showHeadline(String headline);

        void showSnippet(String snippet);

        void showSource(String source);

        void showPublishedDate(String date);

        void showAuthor(String author);

        void loadArticleImage(String url);

        void showErrorMessage();
    }

    interface ArticleDetailsPresenter extends BasePresenter {

        void showArticleDetails(Article article);
    }
}
