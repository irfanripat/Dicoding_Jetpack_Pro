package com.irfan.moviecatalogue.ui.fragment.favourite.favouritetv


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.irfan.moviecatalogue.data.local.entity.TvShow
import com.irfan.moviecatalogue.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteTvViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    fun getFavouriteTv() : LiveData<PagedList<TvShow>> = movieRepository.getAllFavouriteTv()

}