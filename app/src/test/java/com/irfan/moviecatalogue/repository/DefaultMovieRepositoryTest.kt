package com.irfan.moviecatalogue.repository


import com.google.common.truth.Truth.assertThat
import com.irfan.moviecatalogue.data.remote.ApiService
import com.irfan.moviecatalogue.data.remote.entity.ApiResponse
import com.irfan.moviecatalogue.data.remote.entity.MovieResponse
import com.irfan.moviecatalogue.utils.IdlingResource
import com.irfan.moviecatalogue.utils.MainCoroutineRule
import com.irfan.moviecatalogue.utils.Status
import com.nhaarman.mockitokotlin2.mock
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
import retrofit2.Response

@ExperimentalCoroutinesApi
class DefaultMovieRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var idlingResource: IdlingResource
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
        defaultMovieRepository = DefaultMovieRepository(apiService, idlingResource)
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
    fun `error get popular movie, response body should be null`() {
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
    fun `error get popular tv, response body should be null`() {
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
    fun `error when get detail of movie, response status should be error and data should be null`() {
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
    fun `error when get detail of tv, response status should be erro and data should be null`() {
        runBlocking {
            `when`(apiService.getDetailTv(dummyId)).thenReturn(mockErrorMovieResponse)
            val response = defaultMovieRepository.getDetailTv(dummyId)
            assertThat(response.data).isNull()
            assertThat(response.status).isEqualTo(Status.ERROR)
            assertThat(response.data).isEqualTo(mockErrorMovieResponse.body())
        }
    }

}