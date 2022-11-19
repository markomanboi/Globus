package com.example.globus;

import com.example.globus.Models.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<Headlines> getNews(
            @Query("country") String country,
            @Query("apiKey")  String apiKey
    );

    @GET("top-headlines")
    Call<Headlines> getNewswithCategory(
            @Query("country") String country,
            @Query("apiKey")  String apiKey,
            @Query("category") String category
    );

    @GET("everything")
    Call <Headlines> getNewsSearch(
            @Query("q") String keyword,
            @Query("language")  String language,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String api
    );
}
