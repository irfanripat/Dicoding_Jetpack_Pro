package com.irfan.moviecatalogue.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show")
data class TvShow(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int? = -1,

    @ColumnInfo(name = "tv_name")
    var tvName: String? = "",

    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String? = "",

    @ColumnInfo(name = "overview")
    var overview: String? = "",

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = "",

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = "",

    @ColumnInfo(name = "score")
    var score: Double? = 0.0,

    @ColumnInfo(name = "tv_duration")
    var tvDuration: Int? = -1
)
