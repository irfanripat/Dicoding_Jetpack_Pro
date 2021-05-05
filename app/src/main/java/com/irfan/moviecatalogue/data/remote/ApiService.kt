package com.irfan.moviecatalogue.data.remote

import com.irfan.moviecatalogue.BuildConfig
import com.irfan.moviecatalogue.data.remote.entity.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovie(
            @Query("api_key") apiKey : String = BuildConfig.API_KEY
    ) : Response<ApiResponse>

    @GET("tv/popular")
    suspend fun getPopularTv(
            @Query("api_key") apiKey : String = BuildConfig.API_KEY
    ) : Response<ApiResponse>
}