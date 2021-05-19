package com.irfan.moviecatalogue.data.local.dao

import android.provider.ContactsContract
import androidx.paging.DataSource
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.irfan.moviecatalogue.data.local.entity.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * FROM movie")
    fun getAllMovie() : DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovieById(id: Int) : Movie?

}