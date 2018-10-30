package com.underarmour.nytimes.presenter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.underarmour.nytimes.NYTimesApp;
import com.underarmour.nytimes.interactor.SearchInteractor;
import com.underarmour.nytimes.mvp.SearchMVPContract;
import com.underarmour.nytimes.presenter.SearchPresenterImpl;
import com.underarmour.nytimes.utils.NetworkConnectionUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(NYTimesApp.class)
public class ArticleSearchPresenterTest {

    private NetworkConnectionUtil networkConnectionUtil;
    private SearchMVPContract.SearchArticleView searchArticleView;
    private SearchInteractor searchInteractor;
    private SearchMVPContract.SearchPresenter searchPresenter;
    private ConnectivityManager connManager;
    private NetworkInfo networkInfo;

    @Before
    public void setup() {
        searchArticleView = Mockito.mock(SearchMVPContract.SearchArticleView.class);
        searchInteractor = Mockito.mock(SearchInteractor.class);
        searchPresenter = new SearchPresenterImpl();
        searchPresenter.setView(searchArticleView);
        searchPresenter.setInteractor(searchInteractor);

        connManager = Mockito.mock(ConnectivityManager.class);
        networkInfo = Mockito.mock(NetworkInfo.class);
        NYTimesApp app = Mockito.mock(NYTimesApp.class);
        PowerMockito.mockStatic(NYTimesApp.class);
        BDDMockito.when(NYTimesApp.getInstance()).thenReturn(app);
        BDDMockito.when(NYTimesApp.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connManager);

        Mockito.when(connManager.getActiveNetworkInfo()).thenReturn(networkInfo);
        Mockito.when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
        networkConnectionUtil = Mockito.mock(NetworkConnectionUtil.class);
        PowerMockito.verifyStatic();
    }

    @Test
    public void testStartSearchQueryWhenNetworkConnectionIsAvailable() {
        Mockito.when(networkConnectionUtil.isConnected()).thenReturn(true);
        searchPresenter.startSearch("Hurricane");

        Mockito.verify(searchInteractor).searchArticle("Hurricane", Mockito.anyInt());
    }

    @Test
    public void testPresenterShouldNotCallServiceWhenNetworkConnectionIsNotAvailable() {
        Mockito.when(networkConnectionUtil.isConnected()).thenReturn(false);
        searchPresenter.startSearch("Hurricane");

        Mockito.verify(searchArticleView).showNoNetworkErrorMessage();

    }

    @Test
    public void testLoadMoreItemsWhenNetworkConnectionIsAvailable() {
        Mockito.when(networkConnectionUtil.isConnected()).thenReturn(true);
        searchPresenter.loadMoreArticles("Hurricane");

        Mockito.verify(searchInteractor).searchArticle("Hurricane", Mockito.anyInt());

    }

    @Test
    public void testPresenterShouldNotLoadMoreWhenNetworkConnectionIsNotAvailable() {
        Mockito.when(networkConnectionUtil.isConnected()).thenReturn(false);
        searchPresenter.loadMoreArticles("Hurricane");

        Mockito.verify(searchArticleView).showNoNetworkErrorMessage();
    }
}
