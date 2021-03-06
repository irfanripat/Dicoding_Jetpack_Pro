package com.irfan.moviecatalogue.ui.fragment.tv


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfan.moviecatalogue.data.remote.entity.ApiResponse
import com.irfan.moviecatalogue.repository.MovieRepository
import com.irfan.moviecatalogue.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _listTv = MutableLiveData<Resource<ApiResponse>>()
    val listTv :LiveData<Resource<ApiResponse>> = _listTv

    init {
        getPopularTv()
    }

    fun getPopularTv() {
        _listTv.value = Resource.loading(null)
        viewModelScope.launch {
            val response = movieRepository.getPopularTv()
            _listTv.value = response
        }
    }

}