package com.irfan.moviecatalogue.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int? = -1,

    @ColumnInfo(name = "movie_title")
    var movieTitle: String? = "",

    @ColumnInfo(name = "release_date")
    var releaseDate: String? = "",

    @ColumnInfo(name = "overview")
    var overview: String? = "",

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = "",

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = "",

    @ColumnInfo(name = "score")
    var score: Double? = 0.0,

    @ColumnInfo(name = "movie_duration")
    var movieDuration: Int? = null,
)

