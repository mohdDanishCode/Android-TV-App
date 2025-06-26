package com.network.core.dbo.movie

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val posterUrl: String,
    val backdropUrl: String,
    val videoUrl: String,
    val genre: String,
    val duration: String,
    val year: String,
    val rating: Double,
    val isFeatured: Boolean = false,
    val lastUpdated: Long = System.currentTimeMillis()
)

@Entity(tableName = "movie_rails")
data class MovieRailEntity(
    @PrimaryKey val railId: String,
    val title: String,
    val railType: String,
    val orderIndex: Int,
    val lastUpdated: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "rail_movie_cross_ref",
    primaryKeys = ["railId", "movieId"]
)
data class RailMovieCrossRef(
    val railId: String,
    val movieId: String,
    val orderIndex: Int
)

@Entity(tableName = "watch_progress")
data class WatchProgressEntity(
    @PrimaryKey val movieId: String,
    val progressMs: Long,
    val durationMs: Long,
    val progressPercentage: Float,
    val lastWatchedAt: Long = System.currentTimeMillis(),
    val isCompleted: Boolean = false
)


data class MovieRailWithMovies(
    @Embedded val rail: MovieRailEntity,
    @Relation(
        parentColumn = "railId",
        entityColumn = "id",
        associateBy = Junction(
            value = RailMovieCrossRef::class,
            parentColumn = "railId",
            entityColumn = "movieId"
        )
    )
    val movies: List<MovieEntity>
)

data class MovieWithProgress(
    @Embedded val movie: MovieEntity,
    @Embedded val progress: WatchProgressEntity
)


