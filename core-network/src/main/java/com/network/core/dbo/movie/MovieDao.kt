package com.network.core.dbo.movie

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Junction
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getMovieById(id: String): MovieEntity?

    @Query("SELECT * FROM movies WHERE title LIKE :query OR description LIKE :query OR genre LIKE :query")
    suspend fun searchMovies(query: String): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("DELETE FROM movies WHERE lastUpdated < :timestamp")
    suspend fun deleteOldMovies(timestamp: Long)
}

@Dao
interface MovieRailDao {
    @Transaction
    @Query("SELECT * FROM movie_rails ORDER BY orderIndex")
    fun getRailsWithMovies(): Flow<List<MovieRailWithMovies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRails(rails: List<MovieRailEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRailMovieRefs(refs: List<RailMovieCrossRef>)

    @Query("DELETE FROM movie_rails WHERE lastUpdated < :timestamp")
    suspend fun deleteOldRails(timestamp: Long)

    @Query("DELETE FROM rail_movie_cross_ref WHERE railId NOT IN (SELECT railId FROM movie_rails)")
    suspend fun cleanupOrphanedRefs()
}

@Dao
interface WatchProgressDao {
    @Query("SELECT * FROM watch_progress WHERE movieId = :movieId")
    suspend fun getWatchProgress(movieId: String): WatchProgressEntity?

    @Query("""
        SELECT wp.*, m.* FROM watch_progress wp 
        INNER JOIN movies m ON wp.movieId = m.id 
        WHERE wp.progressPercentage > 5 AND wp.progressPercentage < 90 
        ORDER BY wp.lastWatchedAt DESC 
        LIMIT 20
    """)
    fun getContinueWatchingMovies(): Flow<List<MovieWithProgress>>

    @Query("""
        SELECT wp.*, m.* FROM watch_progress wp 
        INNER JOIN movies m ON wp.movieId = m.id 
        ORDER BY wp.lastWatchedAt DESC 
        LIMIT 50
    """)
    fun getWatchHistory(): Flow<List<MovieWithProgress>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWatchProgress(progress: WatchProgressEntity)

    @Query("DELETE FROM watch_progress WHERE movieId = :movieId")
    suspend fun removeFromWatchProgress(movieId: String)
}



