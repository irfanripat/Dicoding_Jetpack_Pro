package com.irfan.moviecatalogue.ui.activity.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse

class DetailViewModel : ViewModel() {
    private val _detailMovie = MutableLiveData<MovieResponse>()
    val detailMovie : LiveData<MovieResponse> = _detailMovie

    fun setData(movie: MovieResponse) {
        _detailMovie.value = movie
    }

}