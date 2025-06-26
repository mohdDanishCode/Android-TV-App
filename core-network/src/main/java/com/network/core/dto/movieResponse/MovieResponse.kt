package com.network.core.dto.movieResponse

import com.network.core.dbo.movie.MovieEntity
import com.network.core.dbo.movie.MovieRailEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    val id: String,
    val title: String,
    val description: String,
    val posterUrl: String,
    val backdropUrl: String,
    val videoUrl: String,
    val genre: String,
    val duration: String,
    val year: String,
    val rating: Double,
    val isFeatured: Boolean = false
)

@JsonClass(generateAdapter = true)
data class MovieRail(
    val railId: String,
    val title: String,
    val railType:RaleType,
    val movies: List<Movie>
){
    @JsonClass(generateAdapter = false)
    enum class RaleType{
        FEATURED_MOVIE,
        CONTINUE_WATCHING,
        STANDARD,
        LARGE
    }
}

@JsonClass(generateAdapter = true)
data class MovieRailsResponse(
    val rails: List<MovieRail>
)

// Data Mappers
fun MovieEntity.toMovie() = Movie(
    id = id,
    title = title,
    description = description,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    videoUrl = videoUrl,
    genre = genre,
    duration = duration,
    year = year,
    rating = rating,
    isFeatured = isFeatured
)

fun Movie.toEntity() = MovieEntity(
    id = id,
    title = title,
    description = description,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    videoUrl = videoUrl,
    genre = genre,
    duration = duration,
    year = year,
    rating = rating,
    isFeatured = isFeatured
)

fun MovieRailEntity.toMovieRail(movies: List<Movie>) = MovieRail(
    railId = railId,
    title = title,
    railType = MovieRail.RaleType.valueOf(railType),
    movies = movies
)

fun MovieRail.toEntity(orderIndex: Int) = MovieRailEntity(
    railId = railId,
    title = title,
    railType = railType.name,
    orderIndex = orderIndex
)