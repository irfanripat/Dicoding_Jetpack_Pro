package com.irfan.moviecatalogue.repository

import com.irfan.moviecatalogue.data.remote.entity.ApiResponse
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse
import com.irfan.moviecatalogue.utils.Resource

interface MovieRepository {

    suspend fun getPopularMovie() : Resource<ApiResponse>

    suspend fun getPopularTv() : Resource<ApiResponse>

    suspend fun getDetailMovie(id : Int) : Resource<MovieResponse>

    suspend fun getDetailTv(id : Int) : Resource<MovieResponse>
}