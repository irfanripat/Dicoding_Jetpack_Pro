package com.irfan.moviecatalogue.fragment.movie

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.moviecatalogue.model.Movie
import com.irfan.moviecatalogue.model.MovieData

class MovieViewModel : ViewModel() {

    private val _listMovie = MutableLiveData<ArrayList<Movie>>()

    fun setData(context: Context) {
        _listMovie.value = MovieData(context).listData
    }

    fun getData() : LiveData<ArrayList<Movie>> = _listMovie
}