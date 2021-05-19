package com.irfan.moviecatalogue.data.local.dao


import androidx.paging.DataSource
import androidx.room.*
import com.irfan.moviecatalogue.data.local.entity.TvShow

@Dao
interface TvDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TvShow)

    @Delete
    suspend fun delete(tvShow: TvShow)

    @Query("SELECT * FROM tv_show")
    fun getAllTvShow() : DataSource.Factory<Int, TvShow>

    @Query("SELECT * FROM tv_show WHERE id = :id")
    suspend fun getTvShowById(id: Int) : TvShow?
}