package com.irfan.moviecatalogue.fragment.tv

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.moviecatalogue.model.Movie
import com.irfan.moviecatalogue.model.TvData

class TvViewModel : ViewModel() {

    private val _listTv = MutableLiveData<ArrayList<Movie>>()

    fun setData(context: Context) {
        _listTv.value = TvData(context).listData
    }

    fun getData() : LiveData<ArrayList<Movie>> = _listTv
}