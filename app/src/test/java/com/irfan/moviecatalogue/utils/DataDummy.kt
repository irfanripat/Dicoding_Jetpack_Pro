package com.irfan.moviecatalogue.utils

import com.irfan.moviecatalogue.data.local.entity.Movie
import com.irfan.moviecatalogue.data.local.entity.TvShow

object DataDummy {

    fun generateDummyMovie() : Movie {
        return Movie(399566, "Godzilla vs. Kong", "2021-03-24", "overview", "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg", "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg", 8.1, 113)
    }

    fun generateDummyTv() : TvShow {
        return TvShow(399566, "Godzilla vs. Kong", "2021-03-24", "overview", "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg", "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg", 8.1, 113)
    }

    fun generateDummyListMovie() : List<Movie> {
        val list = mutableListOf<Movie>()
        for (i in 0..100) {
            val movie = generateDummyMovie()
            movie.id = i
            list.add(movie)
        }
        return list
    }

    fun generateDummyListTv() : List<TvShow> {
        val list = mutableListOf<TvShow>()
        for (i in 0..100) {
            val tv = generateDummyTv()
            tv.id = i
            list.add(tv)
        }
        return list
    }
}