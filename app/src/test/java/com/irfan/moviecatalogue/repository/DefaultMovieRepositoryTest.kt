package com.irfan.moviecatalogue.repository


import androidx.paging.DataSource
import com.google.common.truth.Truth.assertThat
import com.irfan.moviecatalogue.data.local.dao.MovieDao
import com.irfan.moviecatalogue.data.local.dao.TvDao
import com.irfan.moviecatalogue.data.local.entity.Movie
import com.irfan.moviecatalogue.data.local.entity.TvShow
import com.irfan.moviecatalogue.data.remote.ApiService
import com.irfan.moviecatalogue.data.remote.entity.ApiResponse
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse
import com.irfan.moviecatalogue.utils.*
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

@ExperimentalCoroutinesApi
class DefaultMovieRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var idlingResource: IdlingResource
    private lateinit var movieDao: MovieDao
    private lateinit var tvDao: TvDao
    private lateinit var defaultMovieRepository: DefaultMovieRepository

    private val mockSuccessResponse : Response<ApiResponse> = Response.success(mock<ApiResponse>(),
        okhttp3.Response.Builder()
            .code(200)
            .message("Response.success()")
            .protocol(Protocol.HTTP_1_1)
            .request(Request.Builder().url("http://test-url/").build())
            .receivedResponseAtMillis(1619053449513)
            .sentRequestAtMillis(1619053443814)
            .build()
    )

    private val mockErrorResponse : Response<ApiResponse> = Response.error(
        400,
        "{\"key\":[\"somestuff\"]}"
            .toResponseBody("application/json".toMediaTypeOrNull())
    )

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Before
    fun setUp() {
        apiService = mock()
        idlingResource = mock()
        movieDao = mock()
        tvDao = mock()
        defaultMovieRepository = DefaultMovieRepository(apiService, movieDao, tvDao, idlingResource)
    }

    @Test
    fun `success get popular movie, response body should not be null`() {
        runBlocking {
            `when`(apiService.getPopularMovie()).thenReturn(mockSuccessResponse)
            val response = defaultMovieRepository.getPopularMovie()
            assertNotNull(response)
            assertThat(response.data).isEqualTo(mockSuccessResponse.body())
        }
    }

    @Test
    fun `failed get popular movie, response body should be null`() {
        runBlocking {
            `when`(apiService.getPopularMovie()).thenReturn(mockErrorResponse)
            val response = defaultMovieRepository.getPopularMovie()
            assertThat(response.status).isEqualTo(Status.ERROR)
            assertThat(response.data).isEqualTo(mockErrorResponse.body())
        }
    }

    @Test
    fun `success get popular tv, response body should not be null`() {
        runBlocking {
            `when`(apiService.getPopularTv()).thenReturn(mockSuccessResponse)
            val response = defaultMovieRepository.getPopularTv()
            assertNotNull(response)
            assertThat(response.data).isEqualTo(mockSuccessResponse.body())
        }
    }

    @Test
    fun `failed get popular tv, response body should be null`() {
        runBlocking {
            `when`(apiService.getPopularTv()).thenReturn(mockErrorResponse)
            val response = defaultMovieRepository.getPopularMovie()

            assertThat(response.data).isEqualTo(mockErrorResponse.body())
        }
    }

    private val mockSuccessMovieResponse : Response<MovieResponse> = Response.success(mock<MovieResponse>(),
            okhttp3.Response.Builder()
                    .code(200)
                    .message("Response.success()")
                    .protocol(Protocol.HTTP_1_1)
                    .request(Request.Builder().url("http://test-url/").build())
                    .receivedResponseAtMillis(1619053449513)
                    .sentRequestAtMillis(1619053443814)
                    .build()
    )

    private val mockErrorMovieResponse : Response<MovieResponse> = Response.error(
            400,
            "{\"key\":[\"somestuff\"]}"
                    .toResponseBody("application/json".toMediaTypeOrNull())
    )

    private val dummyId = 12345

    @Test
    fun `success when get detail of movie, response status should be success and data should not be null`() {
        runBlocking {
            `when`(apiService.getDetailMovie(dummyId)).thenReturn(mockSuccessMovieResponse)
            val response = defaultMovieRepository.getDetailMovie(dummyId)
            assertThat(response.status).isEqualTo(Status.SUCCESS)
            assertThat(response.data).isNotNull()
            assertThat(response.data).isEqualTo(mockSuccessMovieResponse.body())
        }
    }

    @Test
    fun `failed when get detail of movie, response status should be error and data should be null`() {
        runBlocking {
            `when`(apiService.getDetailMovie(dummyId)).thenReturn(mockErrorMovieResponse)
            val response = defaultMovieRepository.getPopularMovie()
            assertThat(response.data).isNull()
            assertThat(response.status).isEqualTo(Status.ERROR)
            assertThat(response.data).isEqualTo(mockErrorMovieResponse.body())
        }
    }

    @Test
    fun `success when get detail of tv, response status should be success and data should not be null`() {
        runBlocking {
            `when`(apiService.getDetailTv(dummyId)).thenReturn(mockSuccessMovieResponse)
            val response = defaultMovieRepository.getDetailTv(dummyId)
            assertThat(response.status).isEqualTo(Status.SUCCESS)
            assertThat(response.status).isNotNull()
            assertThat(response.data).isEqualTo(mockSuccessMovieResponse.body())
        }
    }

    @Test
    fun `error when get detail of tv, response status should be error and data should be null`() {
        runBlocking {
            `when`(apiService.getDetailTv(dummyId)).thenReturn(mockErrorMovieResponse)
            val response = defaultMovieRepository.getDetailTv(dummyId)
            assertThat(response.data).isNull()
            assertThat(response.status).isEqualTo(Status.ERROR)
            assertThat(response.data).isEqualTo(mockErrorMovieResponse.body())
        }
    }

    private val dummyMovieId = 399566
    private val dummyMovie = DataDummy.generateDummyMovie()
    private val dummyTv = DataDummy.generateDummyTv()

    @Test
    fun `insert movie item to database`() {
        runBlocking {
            defaultMovieRepository.insertMovieItem(dummyMovie)
            verify(movieDao, times(1)).insert(dummyMovie)
        }
    }

    @Test
    fun `insert tv item to database`() {
        runBlocking {
            defaultMovieRepository.insertTvItem(dummyTv)
            verify(tvDao, times(1)).insert(dummyTv)
        }
    }

    @Test
    fun `delete movie item from database`() {
        runBlocking {
            defaultMovieRepository.deleteMovieItem(dummyMovie)
            verify(movieDao, times(1)).delete(dummyMovie)
        }
    }

    @Test
    fun `delete tv item from database`() {
        runBlocking {
            defaultMovieRepository.deleteTvItem(dummyTv)
            verify(tvDao, times(1)).delete(dummyTv)
        }
    }

    @Test
    fun `get movie item by id from db in case movie item is favourite, the return should be not null`() {
        runBlocking {
            `when`(movieDao.getMovieById(dummyMovieId)).thenReturn(dummyMovie)
            val result = defaultMovieRepository.getMovieById(dummyMovieId)
            verify(movieDao, times(1)).getMovieById(dummyMovieId)
            assertThat(result).isNotNull()
            assertThat(result).isEqualTo(dummyMovie)
        }
    }

    @Test
    fun `get tv item by id from db in case tv item is favourite, the return should be not null`() {
        runBlocking {
            `when`(tvDao.getTvShowById(dummyMovieId)).thenReturn(dummyTv)
            val result = defaultMovieRepository.getTvShowById(dummyMovieId)
            verify(tvDao, times(1)).getTvShowById(dummyMovieId)
            assertThat(result).isNotNull()
            assertThat(result).isEqualTo(dummyTv)
        }
    }

    @Test
    fun `get movie item by id from db in case movie item is not favourite, the return should be null`() {
        runBlocking {
            `when`(movieDao.getMovieById(dummyMovieId)).thenReturn(null)
            val result = defaultMovieRepository.getMovieById(dummyMovieId)
            verify(movieDao, times(1)).getMovieById(dummyMovieId)
            assertThat(result).isNull()
        }
    }

    @Test
    fun `get tv item by id from db in case tv item is not favourite, the return should be null`() {
        runBlocking {
            `when`(tvDao.getTvShowById(dummyMovieId)).thenReturn(null)
            val result = defaultMovieRepository.getTvShowById(dummyMovieId)
            verify(tvDao, times(1)).getTvShowById(dummyMovieId)
            assertThat(result).isNull()
        }
    }

    private val dummyListMovie = DataDummy.generateDummyListMovie()
    private val dummyListTv = DataDummy.generateDummyListTv()

    @Test
    fun `get all favourite movie`() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movie>
        `when`(movieDao.getAllMovie()).thenReturn(dataSourceFactory)
        defaultMovieRepository.getAllFavouriteMovie()

        val movieEntities = PagedListUtil.mockPagedList(DataDummy.generateDummyListMovie())
        verify(movieDao).getAllMovie()
        assertThat(movieEntities).isNotNull()
        assertThat(movieEntities.size).isEqualTo(dummyListMovie.size)
        assertThat(movieEntities[15]?.id).isEqualTo(dummyListMovie[15].id)
    }

    @Test
    fun `get all favourite tv`() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShow>
        `when`(tvDao.getAllTvShow()).thenReturn(dataSourceFactory)
        defaultMovieRepository.getAllFavouriteTv()

        val tvEntities = PagedListUtil.mockPagedList(DataDummy.generateDummyListTv())
        verify(tvDao).getAllTvShow()
        assertThat(tvEntities).isNotNull()
        assertThat(tvEntities.size).isEqualTo(dummyListTv.size)
        assertThat(tvEntities[15]?.id).isEqualTo(dummyListTv[15].id)
    }

}