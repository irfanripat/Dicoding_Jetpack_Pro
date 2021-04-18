package com.irfan.moviecatalogue.model

import android.content.Context
import android.os.Parcelable
import com.irfan.moviecatalogue.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String? = "",
    val release: String? = "",
    val overview: String? = "",
    val duration: String? = "",
    val score: Int? = 0,
    val posterImg: Int? = 0
) : Parcelable

class MovieData(context: Context) {
    private val listMovieTitle = context.resources.getStringArray(R.array.list_movie_name)
    private val listMovieReleaseDate = context.resources.getStringArray(R.array.list_movie_release)
    private val listMovieOverview = context.resources.getStringArray(R.array.list_movie_overview)
    private val listMovieDuration = context.resources.getStringArray(R.array.list_movie_duration)
    private val listMovieScore = context.resources.getIntArray(R.array.list_movie_score)
    private val listMoviePosterImage = context.resources.obtainTypedArray(R.array.list_movie_drawable)

    val listData: ArrayList<Movie>
        get() {
            val list = arrayListOf<Movie>()
            for (position in listMovieTitle.indices) {
                val movie = Movie(
                    listMovieTitle[position],
                    listMovieReleaseDate[position],
                    listMovieOverview[position],
                    listMovieDuration[position],
                    listMovieScore[position],
                    listMoviePosterImage.getResourceId(position, -1)
                )
                list.add(movie)
            }
            return list
        }
}

class TvData(context: Context) {
    private val listTvTitle = context.resources.getStringArray(R.array.list_tv_name)
    private val listTvReleaseDate = context.resources.getStringArray(R.array.list_tv_release)
    private val listTvOverview = context.resources.getStringArray(R.array.list_tv_overview)
    private val listTvDuration = context.resources.getStringArray(R.array.list_tv_duration)
    private val listTvScore = context.resources.getIntArray(R.array.list_tv_score)
    private val listTvPosterImage = context.resources.obtainTypedArray(R.array.list_tv_drawable)

    val listData: ArrayList<Movie>
        get() {
            val list = arrayListOf<Movie>()
            for (position in listTvTitle.indices) {
                val movie = Movie(
                    listTvTitle[position],
                    listTvReleaseDate[position],
                    listTvOverview[position],
                    listTvDuration[position],
                    listTvScore[position],
                    listTvPosterImage.getResourceId(position, -1)
                )
                list.add(movie)
            }
            return list
        }
}