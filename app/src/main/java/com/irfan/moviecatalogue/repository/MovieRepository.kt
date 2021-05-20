package com.irfan.moviecatalogue.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.irfan.moviecatalogue.data.local.entity.Movie
import com.irfan.moviecatalogue.data.local.entity.TvShow
import com.irfan.moviecatalogue.data.remote.entity.ApiResponse
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse
import com.irfan.moviecatalogue.utils.Resource

interface MovieRepository {

    suspend fun getPopularMovie() : Resource<ApiResponse>

    suspend fun getPopularTv() : Resource<ApiResponse>

    suspend fun getDetailMovie(id : Int) : Resource<MovieResponse>

    suspend fun getDetailTv(id : Int) : Resource<MovieResponse>

    suspend fun insertMovieItem(movie: Movie)

    suspend fun insertTvItem(tvShow: TvShow)

    suspend fun deleteMovieItem(movie: Movie)

    suspend fun deleteTvItem(tvShow: TvShow)

    fun getAllFavouriteMovie() : LiveData<PagedList<Movie>>

    fun getAllFavouriteTv() : LiveData<PagedList<TvShow>>

    suspend fun getMovieById(id: Int) : Movie?

    suspend fun getTvShowById(id: Int) : TvShow?

}