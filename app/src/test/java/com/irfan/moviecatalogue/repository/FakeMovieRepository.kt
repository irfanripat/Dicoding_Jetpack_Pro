package com.irfan.moviecatalogue.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.irfan.moviecatalogue.data.local.dao.MovieDao
import com.irfan.moviecatalogue.data.local.dao.TvDao
import com.irfan.moviecatalogue.data.local.entity.Movie
import com.irfan.moviecatalogue.data.local.entity.TvShow
import com.irfan.moviecatalogue.data.remote.entity.ApiResponse
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse
import com.irfan.moviecatalogue.utils.Resource


class FakeMovieRepository constructor (private val movieDao: MovieDao, private val tvDao: TvDao) : MovieRepository {

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

    override fun getAllFavouriteMovie(): LiveData<PagedList<Movie>> {
        return LivePagedListBuilder(movieDao.getAllMovie(), 4).build()
    }

    override fun getAllFavouriteTv(): LiveData<PagedList<TvShow>> {
        return LivePagedListBuilder(tvDao.getAllTvShow(), 4).build()
    }

    override suspend fun getMovieById(id: Int): Movie? {
        return movieDao.getMovieById(id)
    }

    override suspend fun getTvShowById(id: Int): TvShow? {
        return tvDao.getTvShowById(id)
    }
}