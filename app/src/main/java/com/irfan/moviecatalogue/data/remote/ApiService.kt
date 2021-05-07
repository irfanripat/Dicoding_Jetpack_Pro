package com.irfan.moviecatalogue.data.remote

import com.irfan.moviecatalogue.BuildConfig
import com.irfan.moviecatalogue.data.remote.entity.ApiResponse
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
            @Path(value = "movie_id", encoded = true) movieId : Int,
            @Query("api_key") apiKey : String = BuildConfig.API_KEY
    ) : Response<MovieResponse>

    @GET("tv/{tv_id}")
    suspend fun getDetailTv(
            @Path(value = "tv_id", encoded = true) tvId : Int,
            @Query("api_key") apiKey : String = BuildConfig.API_KEY
    ) : Response<MovieResponse>

}