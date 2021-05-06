package com.irfan.moviecatalogue.repository

import com.google.common.truth.Truth.assertThat
import com.irfan.moviecatalogue.data.remote.ApiService
import com.irfan.moviecatalogue.data.remote.entity.ApiResponse
import com.irfan.moviecatalogue.utils.MainCoroutineRule
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
        defaultMovieRepository = DefaultMovieRepository(apiService)
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


}