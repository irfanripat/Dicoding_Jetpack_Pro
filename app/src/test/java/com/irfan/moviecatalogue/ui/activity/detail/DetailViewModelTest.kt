package com.irfan.moviecatalogue.ui.activity.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse
import com.irfan.moviecatalogue.repository.FakeMovieRepository
import com.irfan.moviecatalogue.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var fakeMovieRepository: FakeMovieRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        fakeMovieRepository = FakeMovieRepository()
    }

    private val dummyId = 12345
    private val dummyResponse = MovieResponse(id = dummyId)

    @Test
    fun `test if getDetailMovie is error, return should be null`() {
        fakeMovieRepository.setShouldReturnNetworkError(true)
        detailViewModel = DetailViewModel(fakeMovieRepository)
        detailViewModel.getDetailMovie(dummyId)
        val response = detailViewModel.detailItem.getOrAwaitValueTest()
        assertThat(response.status).isEqualTo(Status.ERROR)
        assertThat(response.data).isNull()
    }

    @Test
    fun `test if getDetailMovie is success, return should not be null`() {
        fakeMovieRepository.setShouldReturnNetworkError(false)
        detailViewModel = DetailViewModel(fakeMovieRepository)
        detailViewModel.getDetailMovie(dummyId)
        val response = detailViewModel.detailItem.getOrAwaitValueTest()
        assertThat(response.status).isEqualTo(Status.SUCCESS)
        assertThat(response.data).isNotNull()
        assertThat(response.data).isEqualTo(dummyResponse)
        assertThat(response.data?.id).isEqualTo(dummyId)
    }

    @Test
    fun `test if getDetailTv is error, return should be null`() {
        fakeMovieRepository.setShouldReturnNetworkError(true)
        detailViewModel = DetailViewModel(fakeMovieRepository)
        detailViewModel.getDetailTv(dummyId)
        val response = detailViewModel.detailItem.getOrAwaitValueTest()
        assertThat(response.status).isEqualTo(Status.ERROR)
        assertThat(response.data).isNull()
    }

    @Test
    fun `test if getDetailTv is success, return should not be null`() {
        fakeMovieRepository.setShouldReturnNetworkError(false)
        detailViewModel = DetailViewModel(fakeMovieRepository)
        detailViewModel.getDetailTv(dummyId)
        val response = detailViewModel.detailItem.getOrAwaitValueTest()
        assertThat(response.status).isEqualTo(Status.SUCCESS)
        assertThat(response.data).isNotNull()
        assertThat(response.data).isEqualTo(dummyResponse)
        assertThat(response.data?.id).isEqualTo(dummyId)
    }

}