package com.irfan.moviecatalogue.ui.fragment.tv


import androidx.lifecycle.ViewModel
import com.irfan.moviecatalogue.data.source.local.entity.Movie

class TvViewModel : ViewModel() {

    private var _listTv = ArrayList<Movie>()

    fun setData() {
        _listTv = com.irfan.moviecatalogue.utils.TvData.listData
    }

    fun getData() : ArrayList<Movie> = _listTv
}