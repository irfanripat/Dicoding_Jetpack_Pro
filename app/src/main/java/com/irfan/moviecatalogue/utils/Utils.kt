package com.irfan.moviecatalogue.utils

import android.content.Context
import android.view.View
import com.irfan.moviecatalogue.R
import com.irfan.moviecatalogue.data.local.entity.Movie
import com.irfan.moviecatalogue.data.local.entity.TvShow
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse

object Utils {

    fun View.hide() {
        visibility = View.GONE
    }

    fun View.show() {
        visibility = View.VISIBLE
    }

    fun Double?.rating() : Float {
        if (this != null)
            return this.toFloat() / 2.0f

        return 0.0f
    }

    fun Int?.toClockTime(context: Context) : String {
        return if (this != null) {
            val hour = this / 60
            val minute = this % 60

            if (hour > 0) {
                String.format(context.resources.getString(R.string.runtime_text_for_condition_1), hour, minute)
            } else {
                String.format(context.resources.getString(R.string.runtime_text_for_condition_2), minute)
            }
        } else {
            context.resources.getString(R.string.no_runtime_data_msg)
        }
    }

    infix fun String?.orIfBlank(anotherString: String?) : String{
        if (this.isNullOrBlank()) {
           return anotherString?:""
        }

        return this
    }

    fun MovieResponse.toMovie(): Movie =
        Movie(id, movieTitle, releaseDate, overview, posterPath, backdropPath, score, movieDuration)

    fun MovieResponse.toTvShow(): TvShow =
        TvShow(id, tvName, firstAirDate, overview, posterPath, backdropPath, score, if (tvDuration?.isEmpty() == true) null else tvDuration?.get(0))
}