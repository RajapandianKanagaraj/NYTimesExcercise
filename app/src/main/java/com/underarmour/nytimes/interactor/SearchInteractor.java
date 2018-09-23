package com.underarmour.nytimes.interactor;

import com.underarmour.nytimes.models.SearchResponse;
import com.underarmour.nytimes.network.NYTimesAPIAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchInteractor {

    private SearchResponseListener responseListener;

    public SearchInteractor(SearchResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    public void searchArticle(String searchQuery, int page) {
        NYTimesAPIAdapter.INSTANCE.getNyTimesSearchAPI().getArticleBySearch(searchQuery, page).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse = response.body();
                responseListener.onSuccess(searchResponse);
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                responseListener.onError(t);
            }
        });
    }


    public interface SearchResponseListener {

        void onSuccess(SearchResponse searchResponse);

        void onError(Throwable t);
    }
}
