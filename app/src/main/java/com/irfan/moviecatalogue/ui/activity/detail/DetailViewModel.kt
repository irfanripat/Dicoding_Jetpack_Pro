package com.irfan.moviecatalogue.ui.activity.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse
import com.irfan.moviecatalogue.repository.MovieRepository
import com.irfan.moviecatalogue.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _detailItem = MutableLiveData<Resource<MovieResponse>>()
    val detailItem : LiveData<Resource<MovieResponse>> = _detailItem

    fun getDetailMovie(id: Int) {
        _detailItem.value = Resource.loading(null)
        viewModelScope.launch {
            val response = movieRepository.getDetailMovie(id)
            _detailItem.value = response
        }
    }

    fun getDetailTv(id: Int) {
        _detailItem.value = Resource.loading(null)
        viewModelScope.launch {
            val response = movieRepository.getDetailTv(id)
            _detailItem.value = response
        }
    }

}