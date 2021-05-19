package com.irfan.moviecatalogue.ui.activity.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.irfan.moviecatalogue.data.local.dao.MovieDao
import com.irfan.moviecatalogue.data.local.dao.TvDao
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse
import com.irfan.moviecatalogue.repository.FakeMovieRepository
import com.irfan.moviecatalogue.utils.*
import com.irfan.moviecatalogue.utils.Constants.MOVIE_TYPE
import com.irfan.moviecatalogue.utils.Constants.TV_TYPE
import com.irfan.moviecatalogue.utils.Utils.toMovie
import com.irfan.moviecatalogue.utils.Utils.toTvShow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var fakeMovieRepository: FakeMovieRepository
    private lateinit var movieDao: MovieDao
    private lateinit var tvDao: TvDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        movieDao = mock()
        tvDao = mock()
        fakeMovieRepository = FakeMovieRepository(movieDao, tvDao)
    }

    private val dummyId = 12345
    private val dummyResponse = MovieResponse(id = dummyId)

    @Test
    fun `test if getDetailMovie is failed, return data should be null`() {
        fakeMovieRepository.setShouldReturnNetworkError(true)
        detailViewModel = DetailViewModel(fakeMovieRepository)
        detailViewModel.setTypeOfItem(MOVIE_TYPE)
        detailViewModel.getDetailItem(dummyId)
        val response = detailViewModel.detailItem.getOrAwaitValueTest()
        assertThat(response.status).isEqualTo(Status.ERROR)
        assertThat(response.data).isNull()
    }

    @Test
    fun `test if getDetailMovie is success, return data should not be null`() {
        fakeMovieRepository.setShouldReturnNetworkError(false)
        detailViewModel = DetailViewModel(fakeMovieRepository)
        detailViewModel.setTypeOfItem(MOVIE_TYPE)
        detailViewModel.getDetailItem(dummyId)
        val response = detailViewModel.detailItem.getOrAwaitValueTest()
        assertThat(response.status).isEqualTo(Status.SUCCESS)
        assertThat(response.data).isNotNull()
        assertThat(response.data).isEqualTo(dummyResponse)
        assertThat(response.data?.id).isEqualTo(dummyId)
    }

    @Test
    fun `test if getDetailTv is failed, return data should be null`() {
        fakeMovieRepository.setShouldReturnNetworkError(true)
        detailViewModel = DetailViewModel(fakeMovieRepository)
        detailViewModel.setTypeOfItem(TV_TYPE)
        detailViewModel.getDetailItem(dummyId)
        val response = detailViewModel.detailItem.getOrAwaitValueTest()
        assertThat(response.status).isEqualTo(Status.ERROR)
        assertThat(response.data).isNull()
    }

    @Test
    fun `test if getDetailTv is success, return data should not be null`() {
        fakeMovieRepository.setShouldReturnNetworkError(false)
        detailViewModel = DetailViewModel(fakeMovieRepository)
        detailViewModel.setTypeOfItem(TV_TYPE)
        detailViewModel.getDetailItem(dummyId)
        val response = detailViewModel.detailItem.getOrAwaitValueTest()
        assertThat(response.status).isEqualTo(Status.SUCCESS)
        assertThat(response.data).isNotNull()
        assertThat(response.data).isEqualTo(dummyResponse)
        assertThat(response.data?.id).isEqualTo(dummyId)
    }

    @Test
    fun `set movie item as favourite`() {
        fakeMovieRepository.setShouldReturnNetworkError(false)
        detailViewModel = DetailViewModel(fakeMovieRepository)
        detailViewModel.setTypeOfItem(MOVIE_TYPE)
        detailViewModel.getDetailItem(dummyId)
        detailViewModel.addItemToFavourite()
        runBlocking {
            verify(movieDao).insert(dummyResponse.toMovie())
        }
    }

    @Test
    fun `set tv item as favourite`() {
        fakeMovieRepository.setShouldReturnNetworkError(false)
        detailViewModel = DetailViewModel(fakeMovieRepository)
        detailViewModel.setTypeOfItem(TV_TYPE)
        detailViewModel.getDetailItem(dummyId)
        detailViewModel.addItemToFavourite()
        runBlocking {
            verify(tvDao).insert(dummyResponse.toTvShow())
        }
    }

    @Test
    fun `unset movie item as favourite`() {
        fakeMovieRepository.setShouldReturnNetworkError(false)
        detailViewModel = DetailViewModel(fakeMovieRepository)
        detailViewModel.setTypeOfItem(MOVIE_TYPE)
        detailViewModel.getDetailItem(dummyId)
        detailViewModel.removeItemFromFavourite()
        runBlocking {
            verify(movieDao).delete(dummyResponse.toMovie())
        }
    }

    @Test
    fun `unset tv item as favourite`() {
        fakeMovieRepository.setShouldReturnNetworkError(false)
        detailViewModel = DetailViewModel(fakeMovieRepository)
        detailViewModel.setTypeOfItem(TV_TYPE)
        detailViewModel.getDetailItem(dummyId)
        detailViewModel.removeItemFromFavourite()
        runBlocking {
            verify(tvDao).delete(dummyResponse.toTvShow())
        }
    }

    @Test
    fun `check if movie item is favourite in case movie item is favourite, return should not be true`() {
        runBlocking {
            `when`(movieDao.getMovieById(dummyId)).thenReturn(dummyResponse.toMovie())
        }
        fakeMovieRepository.setShouldReturnNetworkError(false)
        detailViewModel = DetailViewModel(fakeMovieRepository)
        detailViewModel.setTypeOfItem(MOVIE_TYPE)
        val result = detailViewModel.checkIfItemIsFavorite(dummyId).getOrAwaitValueTest()
        runBlocking {
            verify(movieDao).getMovieById(dummyId)
        }
        assertThat(result).isTrue()
    }

    @Test
    fun `check if tv item is favourite in case tv item is favourite, return should not be true`() {
        runBlocking {
            `when`(tvDao.getTvShowById(dummyId)).thenReturn(dummyResponse.toTvShow())
        }
        fakeMovieRepository.setShouldReturnNetworkError(false)
        detailViewModel = DetailViewModel(fakeMovieRepository)
        detailViewModel.setTypeOfItem(TV_TYPE)
        val result = detailViewModel.checkIfItemIsFavorite(dummyId).getOrAwaitValueTest()
        runBlocking {
            verify(tvDao).getTvShowById(dummyId)
        }
        assertThat(result).isTrue()
    }

    @Test
    fun `check if movie item is favourite in case movie item is not favourite, return should be false`() {
        runBlocking {
            `when`(movieDao.getMovieById(dummyId)).thenReturn(null)
        }
        fakeMovieRepository.setShouldReturnNetworkError(false)
        detailViewModel = DetailViewModel(fakeMovieRepository)
        detailViewModel.setTypeOfItem(MOVIE_TYPE)
        val result = detailViewModel.checkIfItemIsFavorite(dummyId).getOrAwaitValueTest()
        runBlocking {
            verify(movieDao).getMovieById(dummyId)
        }
        assertThat(result).isFalse()
    }

    @Test
    fun `check if tv item is favourite in case tv item is not favourite, return should not be false`() {
        runBlocking {
            `when`(tvDao.getTvShowById(dummyId)).thenReturn(null)
        }
        fakeMovieRepository.setShouldReturnNetworkError(false)
        detailViewModel = DetailViewModel(fakeMovieRepository)
        detailViewModel.setTypeOfItem(TV_TYPE)
        val result = detailViewModel.checkIfItemIsFavorite(dummyId).getOrAwaitValueTest()
        runBlocking {
            verify(tvDao).getTvShowById(dummyId)
        }
        assertThat(result).isFalse()
    }

}