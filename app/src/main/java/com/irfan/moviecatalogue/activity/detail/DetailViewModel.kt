package com.irfan.moviecatalogue.activity.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.moviecatalogue.model.Movie

class DetailViewModel : ViewModel() {
    private val _detailMovie = MutableLiveData<Movie>()

    fun setData(movie: Movie) {
        _detailMovie.value = movie
    }

    fun getData() : LiveData<Movie> = _detailMovie
}