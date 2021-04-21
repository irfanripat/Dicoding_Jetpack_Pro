package com.irfan.moviecatalogue.fragment.tv

import android.content.Context
import androidx.lifecycle.ViewModel
import com.irfan.moviecatalogue.model.Movie

class TvViewModel : ViewModel() {

    private var _listTv = ArrayList<Movie>()

    fun setData() {
        _listTv = com.irfan.moviecatalogue.utils.TvData.listData
    }

    fun getData() : ArrayList<Movie> = _listTv
}