package com.example.android.newsapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface GuardianClient {

    @GET("/search")
    Call<Response_> getNews(
            @Query("q") String searchTerm, @Query("api-key") String apiKey
    );

    // The endpoint: https://content.guardianapis.com/search
    // https://content.guardianapis.com/search?q=soccer&api-key=c6762bae-2db4-4235-8650-1f9c37aff2a7
    //  URL url = createUrl(GUARDIAN_API_SEARCH_REQUEST_URL + mSearchTerm + "&api-key=" + GUARDIAN_API_KEY);

}
