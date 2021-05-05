package com.irfan.moviecatalogue.data.remote.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieResponse(
    var original_title: String? = "",
    var original_name: String? = "",
    var release_date: String? = "",
    var first_air_date: String? = "",
    var overview: String? = "",
    var poster_path: String? = "",
    var backdrop_path: String? = "",
    var vote_average: Double? = 0.0
) : Parcelable