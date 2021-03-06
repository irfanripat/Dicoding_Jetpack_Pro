package com.irfan.moviecatalogue.repository

import com.irfan.moviecatalogue.data.remote.entity.ApiResponse
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse
import com.irfan.moviecatalogue.utils.Resource


class FakeMovieRepository : MovieRepository {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getPopularMovie(): Resource<ApiResponse> {
        return if(shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(ApiResponse(arrayListOf()))
        }
    }

    override suspend fun getPopularTv(): Resource<ApiResponse> {
        return if(shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(ApiResponse(arrayListOf()))
        }
    }

    override suspend fun getDetailMovie(id: Int): Resource<MovieResponse> {
        return if(shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(MovieResponse(id = id))
        }
    }

    override suspend fun getDetailTv(id: Int): Resource<MovieResponse> {
        return if(shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(MovieResponse(id = id))
        }
    }
}