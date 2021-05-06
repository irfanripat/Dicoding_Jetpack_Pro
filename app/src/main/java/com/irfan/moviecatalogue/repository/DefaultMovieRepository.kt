package com.irfan.moviecatalogue.repository


import com.irfan.moviecatalogue.data.remote.ApiService
import com.irfan.moviecatalogue.data.remote.entity.ApiResponse
import com.irfan.moviecatalogue.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultMovieRepository @Inject constructor(private val apiService: ApiService) : MovieRepository{

    override suspend fun getPopularMovie(): Resource<ApiResponse> {
        return try {
            val response = apiService.getPopularMovie()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Unknown error", null)
            } else {
                Resource.error("Unknown error", null)
            }
        } catch (e: Exception) {
            Resource.error("No Internet connection", null)
        }
    }

    override suspend fun getPopularTv(): Resource<ApiResponse> {
        return try {
            val response = apiService.getPopularTv()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Unknown error", null)
            } else {
                Resource.error("Unknown error", null)
            }
        } catch (e: Exception) {
            Resource.error("No Internet connection", null)
        }
    }

}

