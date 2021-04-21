package com.irfan.moviecatalogue.fragment.movie



import android.util.Log
import com.irfan.moviecatalogue.utils.MovieData
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test


class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel
    private val dummyMovie = MovieData.listData

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel()
    }

    @Test
    fun `check return of getData, should not be null`() {
        movieViewModel.setData()
        val movie = movieViewModel.getData()
        Log.d("movie", movie.toString())
        assertNotNull(movie)
        assertEquals(dummyMovie, movie)
    }

}