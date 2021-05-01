package com.irfan.moviecatalogue.ui.activity.detail


import androidx.lifecycle.ViewModel
import com.irfan.moviecatalogue.data.source.local.entity.Movie

class DetailViewModel : ViewModel() {
    private var _detailMovie = Movie()

    fun setData(movie: Movie) {
        _detailMovie = movie
    }

    fun getData() : Movie = _detailMovie

}