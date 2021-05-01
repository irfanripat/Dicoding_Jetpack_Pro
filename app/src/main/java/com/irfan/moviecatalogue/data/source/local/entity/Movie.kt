package com.irfan.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String? = "",
    val release: String? = "",
    val overview: String? = "",
    val duration: String? = "",
    val score: Int? = 0,
    val posterImg: String? = ""
) : Parcelable

