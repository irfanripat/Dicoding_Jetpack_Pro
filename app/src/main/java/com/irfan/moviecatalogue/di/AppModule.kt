package com.irfan.moviecatalogue.di


import com.irfan.moviecatalogue.data.remote.ApiService
import com.irfan.moviecatalogue.repository.DefaultMovieRepository
import com.irfan.moviecatalogue.repository.MovieRepository
import com.irfan.moviecatalogue.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
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