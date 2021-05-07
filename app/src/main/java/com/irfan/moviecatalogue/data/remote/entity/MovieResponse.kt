package com.irfan.moviecatalogue.data.remote.entity

import com.google.gson.annotations.SerializedName


data class MovieResponse(
        var id: Int? = -1,

        @field:SerializedName("original_title")
        var movieTitle: String? = "",

        @field:SerializedName("original_name")
        var tvName: String? = "",

        @field:SerializedName("release_date")
        var releaseDate: String? = "",

        @field:SerializedName("first_air_date")
        var firstAirDate: String? = "",

        @field:SerializedName("overview")
        var overview: String? = "",

        @field:SerializedName("poster_path")
        var posterPath: String? = "",

        @field:SerializedName("backdrop_path")
        var backdropPath: String? = "",

        @field:SerializedName("vote_average")
        var score: Double? = 0.0,

        @field:SerializedName("runtime")
        var movieDuration: Int? = null,

        @field:SerializedName("episode_run_time")
        var tvDuration: List<Int>? = emptyList()
)