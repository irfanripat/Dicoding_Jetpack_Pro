package com.irfan.moviecatalogue.data.source.remote

import com.irfan.moviecatalogue.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovie(@Query("api_key") apiKey : String = BuildConfig.API_KEY)

    @GET("tv/popular")
    fun getPopularTv(@Query("api_key") apiKey : String = BuildConfig.API_KEY)
}