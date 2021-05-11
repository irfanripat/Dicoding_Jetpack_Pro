package com.irfan.moviecatalogue.ui.fragment.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.irfan.moviecatalogue.data.remote.entity.ApiResponse
import com.irfan.moviecatalogue.repository.FakeMovieRepository
import com.irfan.moviecatalogue.utils.MainCoroutineRule
import com.irfan.moviecatalogue.utils.Status
import com.irfan.moviecatalogue.utils.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var fakeMovieRepository: FakeMovieRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        fakeMovieRepository = FakeMovieRepository()
    }

    @Test
    fun `test failed to get popular movie, the return should be Error`() {
        fakeMovieRepository.setShouldReturnNetworkError(true)
        movieViewModel = MovieViewModel(fakeMovieRepository)

        val response = movieViewModel.listMovie.getOrAwaitValueTest()
        assertThat(response.status).isEqualTo(Status.ERROR)
        assertThat(response.data).isNull()
    }

    @Test
    fun `test success to get popular movie, the return should be success`() {
        fakeMovieRepository.setShouldReturnNetworkError(false)
        movieViewModel = MovieViewModel(fakeMovieRepository)

        val response = movieViewModel.listMovie.getOrAwaitValueTest()
        assertThat(response.status).isEqualTo(Status.SUCCESS)
        assertThat(response.data).isNotNull()
        assertThat(response.data).isEqualTo(ApiResponse(arrayListOf()))
    }

}