package com.irfan.moviecatalogue.repository


import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.irfan.moviecatalogue.data.local.dao.MovieDao
import com.irfan.moviecatalogue.data.local.dao.TvDao
import com.irfan.moviecatalogue.data.local.entity.Movie
import com.irfan.moviecatalogue.data.local.entity.TvShow
import com.irfan.moviecatalogue.data.remote.ApiService
import com.irfan.moviecatalogue.data.remote.entity.ApiResponse
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse
import com.irfan.moviecatalogue.utils.EspressoIdlingResource
import com.irfan.moviecatalogue.utils.IdlingResource
import com.irfan.moviecatalogue.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultMovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao,
    private val tvDao: TvDao,
    private val idlingResource: IdlingResource = EspressoIdlingResource
) : MovieRepository{
    override suspend fun getPopularMovie(): Resource<ApiResponse> {
        idlingResource.increment()
        return try {
            val response = apiService.getPopularMovie()
            if (response.isSuccessful) {
                idlingResource.decrement()
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Unknown error", null)
            } else {
                idlingResource.decrement()
                Resource.error("Unknown error", null)
            }
        } catch (e: Exception) {
            idlingResource.decrement()
            Resource.error("No Internet connection", null)
        }
    }

    override suspend fun getPopularTv(): Resource<ApiResponse> {
        idlingResource.increment()
        return try {
            val response = apiService.getPopularTv()
            if (response.isSuccessful) {
                idlingResource.decrement()
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Unknown error", null)
            } else {
                idlingResource.decrement()
                Resource.error("Unknown error", null)
            }
        } catch (e: Exception) {
            idlingResource.decrement()
            Resource.error("No Internet connection", null)
        }
    }

    override suspend fun getDetailMovie(id: Int): Resource<MovieResponse> {
        idlingResource.increment()
        return try {
            val response = apiService.getDetailMovie(id)
            if (response.isSuccessful) {
                idlingResource.decrement()
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Unknown Error", null)
            } else {
                idlingResource.decrement()
                Resource.error("Unknown Error", null)
            }
        } catch (e: Exception) {
            idlingResource.decrement()
            Resource.error("No Internet connection", null)
        }
    }

    override suspend fun getDetailTv(id: Int): Resource<MovieResponse> {
        idlingResource.increment()
        return try {
            val response = apiService.getDetailTv(id)
            if (response.isSuccessful) {
                idlingResource.decrement()
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Unknown Error", null)
            } else {
                idlingResource.decrement()
                Resource.error("Unknown Error", null)
            }
        } catch (e: Exception) {
            idlingResource.decrement()
            Resource.error("No Internet connection", null)
        }
    }

    override suspend fun insertMovieItem(movie: Movie) {
        idlingResource.increment()
        movieDao.insert(movie)
        idlingResource.decrement()
    }

    override suspend fun insertTvItem(tvShow: TvShow) {
        idlingResource.increment()
        tvDao.insert(tvShow)
        idlingResource.decrement()
    }

    override suspend fun deleteMovieItem(movie: Movie) {
        idlingResource.increment()
        movieDao.delete(movie)
        idlingResource.decrement()
    }

    override suspend fun deleteTvItem(tvShow: TvShow) {
        idlingResource.increment()
        tvDao.delete(tvShow)
        idlingResource.decrement()
    }

    override fun getAllFavouriteMovie(): LiveData<PagedList<Movie>> {
        idlingResource.increment()
        val result =  LivePagedListBuilder(movieDao.getAllMovie(), 4).build()
        idlingResource.decrement()
        return result
    }

    override fun getAllFavouriteTv(): LiveData<PagedList<TvShow>> {
        idlingResource.increment()
        val result =  LivePagedListBuilder(tvDao.getAllTvShow(), 4).build()
        idlingResource.decrement()
        return result
    }

    override suspend fun getMovieById(id: Int): Movie? {
        idlingResource.increment()
        val result = movieDao.getMovieById(id)
        idlingResource.decrement()
        return result
    }

    override suspend fun getTvShowById(id: Int): TvShow? {
        idlingResource.increment()
        val result = tvDao.getTvShowById(id)
        idlingResource.decrement()
        return result
    }

}

