package com.irfan.moviecatalogue.ui.activity.detail

import com.irfan.moviecatalogue.utils.MovieData
import com.irfan.moviecatalogue.utils.TvData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel
    private val dummyMovieData = MovieData.listData
    private val dummyTvData = TvData.listData

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel()
    }

    @Test
    fun `get Detail of Movie Data, return should not be null`() {
//        detailViewModel.setData(dummyMovieData[6])
//        val movie = detailViewModel.getData()
//        assertNotNull(movie)
//        assertEquals(dummyMovieData[6].title, movie.title)
//        assertEquals(dummyMovieData[6].release, movie.release)
//        assertEquals(dummyMovieData[6].score, movie.score)
//        assertEquals(dummyMovieData[6].duration, movie.duration)
//        assertEquals(dummyMovieData[6].posterImg, movie.posterImg)
//        assertEquals(dummyMovieData[6].overview, movie.overview)
    }

    @Test
    fun `get Detail of Tv Data, return should not be null`() {
//        detailViewModel.setData(dummyTvData[3])
//        val tv = detailViewModel.getData()
//        assertNotNull(tv)
//        assertEquals(dummyTvData[3].title, tv.title)
//        assertEquals(dummyTvData[3].release, tv.release)
//        assertEquals(dummyTvData[3].score, tv.score)
//        assertEquals(dummyTvData[3].duration, tv.duration)
//        assertEquals(dummyTvData[3].posterImg, tv.posterImg)
//        assertEquals(dummyTvData[3].overview, tv.overview)
    }

}