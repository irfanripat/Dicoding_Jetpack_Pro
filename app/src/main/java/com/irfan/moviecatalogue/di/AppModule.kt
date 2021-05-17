package com.irfan.moviecatalogue.di


import android.content.Context
import androidx.room.Room
import com.irfan.moviecatalogue.data.local.MovieDatabase
import com.irfan.moviecatalogue.data.local.dao.MovieDao
import com.irfan.moviecatalogue.data.local.dao.TvDao
import com.irfan.moviecatalogue.data.remote.ApiService
import com.irfan.moviecatalogue.repository.DefaultMovieRepository
import com.irfan.moviecatalogue.repository.MovieRepository
import com.irfan.moviecatalogue.utils.Constants.BASE_URL
import com.irfan.moviecatalogue.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        apiService: ApiService,
        movieDao: MovieDao,
        tvDao: TvDao
    ) = DefaultMovieRepository(apiService, movieDao, tvDao) as MovieRepository

    @Provides
    @Singleton
    fun provideApiService() : ApiService {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, MovieDatabase::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideMovieDao(
        database: MovieDatabase
    ) = database.movieDao()

    @Provides
    @Singleton
    fun provideTvDao(
        database: MovieDatabase
    ) = database.tvDao()

}