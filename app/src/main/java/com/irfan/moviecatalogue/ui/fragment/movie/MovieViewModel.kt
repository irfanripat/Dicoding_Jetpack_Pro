package com.irfan.moviecatalogue.ui.fragment.movie


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
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _listMovie = MutableLiveData<Resource<ApiResponse>>()
    val listMovie: LiveData<Resource<ApiResponse>> = _listMovie

    init {
        getPopularMovie()
    }

    fun getPopularMovie() {
        _listMovie.value = Resource.loading(null)
        viewModelScope.launch {
            val response = movieRepository.getPopularMovie()
            _listMovie.value = response
        }
    }

}