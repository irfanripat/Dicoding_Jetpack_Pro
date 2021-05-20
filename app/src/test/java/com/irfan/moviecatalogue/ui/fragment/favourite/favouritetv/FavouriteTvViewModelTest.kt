package com.irfan.moviecatalogue.ui.fragment.favourite.favouritetv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.google.common.truth.Truth
import com.irfan.moviecatalogue.data.local.entity.TvShow
import com.irfan.moviecatalogue.repository.DefaultMovieRepository
import com.irfan.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import java.util.concurrent.Executors

class FavouriteTvViewModelTest {
    private lateinit var viewModel : FavouriteTvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: DefaultMovieRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShow>>

    @Before
    fun setUp() {
        movieRepository = mock()
        observer = mock()
        viewModel = FavouriteTvViewModel(movieRepository)
    }

    private val dummyListTv = DataDummy.generateDummyListTv()

    @Test
    fun `get all favourite tv in case favourite tv is not empty, return should not empty`() {
        val expected = MutableLiveData<PagedList<TvShow>>()
        expected.value = PagedTestDataSources.snapshot(dummyListTv)
        Mockito.`when`(movieRepository.getAllFavouriteTv()).thenReturn(expected)
        viewModel.getFavouriteTv().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavouriteTv().value
        Truth.assertThat(actualValue).isNotEmpty()
        Truth.assertThat(expectedValue).isEqualTo(actualValue)
        Truth.assertThat(expectedValue?.snapshot()).isEqualTo(actualValue?.snapshot())
        Truth.assertThat(expectedValue?.size).isEqualTo(actualValue?.size)
    }

    @Test
    fun `get all favourite tv in case favourite tv is empty, return should be empty`() {
        val expected = MutableLiveData<PagedList<TvShow>>()
        expected.value = PagedTestDataSources.snapshot()
        Mockito.`when`(movieRepository.getAllFavouriteTv()).thenReturn(expected)
        viewModel.getFavouriteTv().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavouriteTv().value
        Truth.assertThat(actualValue).isEmpty()
        Truth.assertThat(expectedValue).isEqualTo(actualValue)
        Truth.assertThat(expectedValue?.snapshot()).isEqualTo(actualValue?.snapshot())
        Truth.assertThat(expectedValue?.size).isEqualTo(actualValue?.size)
    }



    class PagedTestDataSources private constructor(private val items: List<TvShow>) : PositionalDataSource<TvShow>() {
        companion object {
            fun snapshot(items: List<TvShow> = listOf()): PagedList<TvShow> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                        .setNotifyExecutor(Executors.newSingleThreadExecutor())
                        .setFetchExecutor(Executors.newSingleThreadExecutor())
                        .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<TvShow>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TvShow>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}