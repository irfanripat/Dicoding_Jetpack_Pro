package com.irfan.moviecatalogue.di


import com.irfan.moviecatalogue.data.remote.ApiService
import com.irfan.moviecatalogue.repository.DefaultMovieRepository
import com.irfan.moviecatalogue.repository.MovieRepository
import com.irfan.moviecatalogue.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        apiService: ApiService
    ) = DefaultMovieRepository(apiService) as MovieRepository

    @Provides
    @Singleton
    fun provideApiService() : ApiService {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
    }

}