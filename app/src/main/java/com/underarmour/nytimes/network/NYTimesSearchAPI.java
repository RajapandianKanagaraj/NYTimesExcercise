package com.underarmour.nytimes.network;

import com.underarmour.nytimes.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NYTimesSearchAPI {

    @GET(APIConstants.SEARCH_PATH)
    Call<SearchResponse> getArticleBySearch(@Query(APIConstants.SEARCH_QUERY_KEY) String queryString,
                                            @Query(APIConstants.PAGE_QUERY_KEY) int page);
}
