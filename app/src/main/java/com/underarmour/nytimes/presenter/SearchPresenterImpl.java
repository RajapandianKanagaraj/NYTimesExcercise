package com.underarmour.nytimes.presenter;

import com.underarmour.nytimes.interactor.SearchInteractor;
import com.underarmour.nytimes.models.Response;
import com.underarmour.nytimes.models.SearchResponse;
import com.underarmour.nytimes.mvp.SearchMVPContract;
import com.underarmour.nytimes.mvp.base.BaseView;
import com.underarmour.nytimes.utils.NetworkConnectionUtil;

import java.io.IOException;

public class SearchPresenterImpl implements SearchMVPContract.SearchPresenter, SearchInteractor.SearchResponseListener {

    private int currentPage = 0;
    private SearchMVPContract.SearchArticleView searchArticleView;
    private SearchInteractor searchInteractor;
    private boolean isNewSearch;
    private String searchQuery;

    public SearchPresenterImpl() {
        searchInteractor = new SearchInteractor(this);
    }
    @Override
    public void startSearch(String searchQuery) {
        isNewSearch = true;
        this.searchQuery = searchQuery;
        if(new NetworkConnectionUtil().isConnected()) {
            searchInteractor.searchArticle(searchQuery, currentPage);
        } else {
            searchArticleView.showNoNetworkErrorMessage();
        }
    }

    @Override
    public void loadMoreArticles(String searchQuery) {
        isNewSearch = false;
        if(new NetworkConnectionUtil().isConnected()) {
            searchInteractor.searchArticle(searchQuery, currentPage);
        } else {
            searchArticleView.showNoNetworkErrorMessage();
        }
    }

    @Override
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public void setView(BaseView baseView) {
        searchArticleView = (SearchMVPContract.SearchArticleView) baseView;
    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void onViewPause() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void onSuccess(SearchResponse searchResponse) {
        if(searchResponse != null) {
            Response response = searchResponse.getResponse();
            if(response != null) {
                currentPage += 1;
                setCurrentPage(currentPage);
                if(isNewSearch) {
                    searchArticleView.showSearchQuery(searchQuery);
                }
                searchArticleView.showArticleList(response.getArticles());
            }
        }
    }

    @Override
    public void onError(Throwable t) {
        if (t instanceof IOException) {
            searchArticleView.showNoNetworkErrorMessage();
        } else {
            searchArticleView.showNoArticlesErrorMessage();
        }
    }

    public void setInteractor(SearchInteractor searchInteractor) {
        this.searchInteractor = searchInteractor;
    }
}
