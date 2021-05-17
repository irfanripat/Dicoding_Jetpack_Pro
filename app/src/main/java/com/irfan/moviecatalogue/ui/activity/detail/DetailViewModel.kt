package com.irfan.moviecatalogue.ui.activity.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse
import com.irfan.moviecatalogue.repository.MovieRepository
import com.irfan.moviecatalogue.utils.Constants.MOVIE_TYPE
import com.irfan.moviecatalogue.utils.Resource
import com.irfan.moviecatalogue.utils.Utils.toMovie
import com.irfan.moviecatalogue.utils.Utils.toTvShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _detailItem = MutableLiveData<Resource<MovieResponse>>()
    val detailItem : LiveData<Resource<MovieResponse>> = _detailItem

    private lateinit var typeOfItem: String

    fun setTypeOfItem(type: String?) {
        typeOfItem = type?:""
    }

    fun getDetailItem(id: Int) {
        _detailItem.value = Resource.loading(null)
        viewModelScope.launch {
           val response = if (typeOfItem == MOVIE_TYPE) {
                movieRepository.getDetailMovie(id)
            } else {
                movieRepository.getDetailTv(id)
            }
            _detailItem.value = response
        }
    }

    fun addItemToFavourite() = viewModelScope.launch {
        if (typeOfItem == MOVIE_TYPE) {
            detailItem.value?.data?.toMovie()?.let { movieRepository.insertMovieItem(it) }
        } else {
            detailItem.value?.data?.toTvShow()?.let { movieRepository.insertTvItem(it) }
        }
    }

    fun removeItemFromFavourite() = viewModelScope.launch {
        if (typeOfItem == MOVIE_TYPE) {
            detailItem.value?.data?.toMovie()?.let { movieRepository.deleteMovieItem(it) }
        } else {
            detailItem.value?.data?.toTvShow()?.let { movieRepository.deleteTvItem(it) }
        }
    }

    fun checkIfItemIsFavorite(id: Int) : LiveData<Boolean> {
        val isFavorite = MutableLiveData<Boolean>()
        viewModelScope.launch {
            if (typeOfItem == MOVIE_TYPE) {
                isFavorite.postValue(movieRepository.getMovieById(id)?.id == id)
            } else {
                isFavorite.postValue(movieRepository.getTvShowById(id)?.id == id)
            }
        }
        return isFavorite
    }

}