package com.irfan.moviecatalogue.data.local

import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.RoomDatabase
import com.irfan.moviecatalogue.data.local.dao.MovieDao
import com.irfan.moviecatalogue.data.local.dao.TvDao
import com.irfan.moviecatalogue.data.local.entity.Movie
import com.irfan.moviecatalogue.data.local.entity.TvShow
import java.util.concurrent.Executors

@Database(
    entities = [Movie::class, TvShow::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

    abstract fun tvDao() : TvDao

}