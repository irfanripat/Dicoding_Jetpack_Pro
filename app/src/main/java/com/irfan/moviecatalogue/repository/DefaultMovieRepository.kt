package com.irfan.moviecatalogue.repository


import androidx.lifecycle.LiveData
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
        movieDao.insert(movie)
    }

    override suspend fun insertTvItem(tvShow: TvShow) {
        tvDao.insert(tvShow)
    }

    override suspend fun deleteMovieItem(movie: Movie) {
        movieDao.delete(movie)
    }

    override suspend fun deleteTvItem(tvShow: TvShow) {
        tvDao.delete(tvShow)
    }

    override fun observeAllMovie(): LiveData<List<Movie>> {
        return movieDao.observeAllMovie()
    }

    override fun observeAllTvShow(): LiveData<List<TvShow>> {
        return tvDao.observeAllTvShow()
    }

    override suspend fun getMovieById(id: Int): Movie? {
        return movieDao.getMovieById(id)
    }

    override suspend fun getTvShowById(id: Int): TvShow? {
        return tvDao.getTvShowById(id)
    }

}

