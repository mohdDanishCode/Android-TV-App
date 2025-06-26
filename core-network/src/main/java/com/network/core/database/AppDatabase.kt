package com.network.core.database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.network.core.dbo.movie.MovieDao
import com.network.core.dbo.movie.MovieEntity
import com.network.core.dbo.movie.MovieRailDao
import com.network.core.dbo.movie.MovieRailEntity
import com.network.core.dbo.movie.RailMovieCrossRef
import com.network.core.dbo.movie.WatchProgressDao
import com.network.core.dbo.movie.WatchProgressEntity


@Database(entities = [ MovieEntity::class, MovieRailEntity::class, RailMovieCrossRef::class, WatchProgressEntity::class], version = 7, exportSchema = false)
@TypeConverters()
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieRailDao(): MovieRailDao
    abstract fun watchProgressDao(): WatchProgressDao
}