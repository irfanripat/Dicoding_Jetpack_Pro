package com.irfan.moviecatalogue.ui.fragment.favourite.favouritemovie


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.irfan.moviecatalogue.data.local.entity.Movie
import com.irfan.moviecatalogue.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteMovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    fun getFavouriteMovie() : LiveData<PagedList<Movie>> = movieRepository.getAllFavouriteMovie()

}