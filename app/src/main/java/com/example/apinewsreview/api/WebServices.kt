package com.example.apinewsreview.api

import com.example.apinewsreview.pojo.NewsResponse
import com.example.apinewsreview.pojo.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("sources")
    fun getSources(@Query("apiKey")key: String,
                    @Query("language")lang: String,
                    @Query("country")country: String):Call<SourcesResponse>


    @GET("everything")
    fun getNews(@Query("apiKey")key: String,
                   @Query("language")lang: String,
                   @Query("sources")sources: String,
                    @Query("q")searchKeywords: String):Call<NewsResponse>

}