package com.underarmour.nytimes.mvp;

import com.underarmour.nytimes.interactor.SearchInteractor;
import com.underarmour.nytimes.models.Article;
import com.underarmour.nytimes.mvp.base.BasePresenter;
import com.underarmour.nytimes.mvp.base.BaseView;

import java.util.ArrayList;

public interface SearchMVPContract {

    interface SearchArticleView extends BaseView {

        void showArticleList(ArrayList<Article> articleList);

        void showSearchQuery(String searchQueryText);;

        void showNoArticlesErrorMessage();

        void showNoNetworkErrorMessage();

    }

    interface SearchPresenter extends BasePresenter {

        void startSearch(String searchQuery);

        void loadMoreArticles(String searchQuery);

        void setCurrentPage(int currentPage);

        int getCurrentPage();

        void setInteractor(SearchInteractor searchInteractor);

    }
}
