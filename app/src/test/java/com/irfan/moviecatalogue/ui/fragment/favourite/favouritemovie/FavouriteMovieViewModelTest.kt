package com.irfan.moviecatalogue.ui.fragment.favourite.favouritemovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.irfan.moviecatalogue.data.local.entity.Movie
import com.irfan.moviecatalogue.repository.DefaultMovieRepository
import com.irfan.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.util.concurrent.Executors


class FavouriteMovieViewModelTest {

    private lateinit var viewModel : FavouriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: DefaultMovieRepository

    @Mock
    private lateinit var observer: Observer<PagedList<Movie>>

    @Before
    fun setUp() {
        movieRepository = mock()
        observer = mock()
        viewModel = FavouriteMovieViewModel(movieRepository)
    }

    private val dummyListMovie = DataDummy.generateDummyListMovie()

    @Test
    fun `get all favourite movie in case favourite movie is not empty, return should not empty`() {
        val expected = MutableLiveData<PagedList<Movie>>()
        expected.value = PagedTestDataSources.snapshot(dummyListMovie)
        `when`(movieRepository.getAllFavouriteMovie()).thenReturn(expected)
        viewModel.getFavouriteMovie().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavouriteMovie().value
        assertThat(actualValue).isNotEmpty()
        assertThat(expectedValue).isEqualTo(actualValue)
        assertThat(expectedValue?.snapshot()).isEqualTo(actualValue?.snapshot())
        assertThat(expectedValue?.size).isEqualTo(actualValue?.size)
    }

    @Test
    fun `get all favourite movie in case favourite movie is empty, return should be empty`() {
        val expected = MutableLiveData<PagedList<Movie>>()
        expected.value = PagedTestDataSources.snapshot()
        `when`(movieRepository.getAllFavouriteMovie()).thenReturn(expected)
        viewModel.getFavouriteMovie().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavouriteMovie().value
        assertThat(actualValue).isEmpty()
        assertThat(expectedValue).isEqualTo(actualValue)
        assertThat(expectedValue?.snapshot()).isEqualTo(actualValue?.snapshot())
        assertThat(expectedValue?.size).isEqualTo(actualValue?.size)
    }



    class PagedTestDataSources private constructor(private val items: List<Movie>) : PositionalDataSource<Movie>() {
        companion object {
            fun snapshot(items: List<Movie> = listOf()): PagedList<Movie> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                        .setNotifyExecutor(Executors.newSingleThreadExecutor())
                        .setFetchExecutor(Executors.newSingleThreadExecutor())
                        .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Movie>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Movie>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}