package com.irfan.moviecatalogue.ui.fragment.movie


import androidx.lifecycle.ViewModel
import com.irfan.moviecatalogue.data.MovieRepository
import com.irfan.moviecatalogue.data.source.local.entity.Movie
import com.irfan.moviecatalogue.utils.MovieData

class MovieViewModel : ViewModel() {

    private var _listMovie = ArrayList<Movie>()

    fun setData() {
        _listMovie = MovieData.listData
    }

    fun getData() : ArrayList<Movie> = _listMovie
}