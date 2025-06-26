package com.core.coreDatasource.repository


import com.network.core.dbo.movie.MovieDao
import com.network.core.dbo.movie.MovieRailDao
import com.network.core.dbo.movie.RailMovieCrossRef
import com.network.core.dbo.movie.WatchProgressDao
import com.network.core.dbo.movie.WatchProgressEntity
import com.network.core.dto.movieResponse.Movie
import com.network.core.dto.movieResponse.MovieRailsResponse
import com.network.core.dto.movieResponse.toEntity
import com.network.core.dto.movieResponse.toMovie
import com.network.core.dto.movieResponse.toMovieRail
import com.network.core.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao,
    private val movieRailDao: MovieRailDao,
    private val watchProgressDao: WatchProgressDao,
) : BaseRepository() {

    val hasInternet
        get() = true


    private val cacheTimeoutMs = 0L

    // DB First
    fun getMovies(): Flow<MovieRailsResponse> = flow {
        movieRailDao.getRailsWithMovies().collect { railsWithMovies ->
            if (railsWithMovies.isNotEmpty()) {
                val movieRails = railsWithMovies.map { railWithMovies ->
                    railWithMovies.rail.toMovieRail(
                        railWithMovies.movies.map { it.toMovie() }
                    )
                }
                emit(MovieRailsResponse(movieRails))
            }

            val shouldRefresh = railsWithMovies.isEmpty() ||
                    railsWithMovies.any {
                        System.currentTimeMillis() - it.rail.lastUpdated > cacheTimeoutMs
                    }

            if (shouldRefresh && hasInternet) {
                refreshMoviesFromApi()
            }
        }
    }

    /**
     * Get single movie by ID from database
     */
    fun getMovieById(id: String): Flow<Movie?> = flow {
        movieDao.getMovieById(id)?.let { movieEntity ->
            emit(movieEntity.toMovie())
        } ?: run {
            emit(null)
            if (hasInternet) {
                refreshMoviesFromApi()
                movieDao.getMovieById(id)?.let {
                    emit(it.toMovie())
                }
            }
        }
    }


    /**
     * Get continue watching movies
     */
    fun getContinueWatchingMovies(): Flow<List<Movie>> =
        watchProgressDao.getContinueWatchingMovies().map { moviesWithProgress ->
            moviesWithProgress.map { it.movie.toMovie() }
        }

    /**
     * Get watch history
     */
    fun getWatchHistory(): Flow<List<Movie>> =
        watchProgressDao.getWatchHistory().map { moviesWithProgress ->
            moviesWithProgress.map { it.movie.toMovie() }
        }

    /**
     * Update watch progress
     */
    suspend fun updateWatchProgress(
        movieId: String,
        progressMs: Long,
        durationMs: Long
    ) {
        val progressPercentage = (progressMs.toFloat() / durationMs.toFloat()) * 100f
        val watchProgress = WatchProgressEntity(
            movieId = movieId,
            progressMs = progressMs,
            durationMs = durationMs,
            progressPercentage = progressPercentage,
            isCompleted = progressPercentage >= 90f
        )
        watchProgressDao.updateWatchProgress(watchProgress)
    }

    /**
     * Force refresh movies from API
     */
    private suspend fun refreshMoviesFromApi() {
        try {
            val response = apiCall("getMovies") {
                apiService.getMovies()
            }

            response.data?.let { movieRailsResponse ->
                // Clear old data
                val currentTime = System.currentTimeMillis()
                movieRailDao.deleteOldRails(currentTime - cacheTimeoutMs)
                movieDao.deleteOldMovies(currentTime - cacheTimeoutMs)
                movieRailDao.cleanupOrphanedRefs()

                // Insert new data
                val allMovies = movieRailsResponse.rails.flatMap { rail ->
                    rail.movies.map { it.toEntity() }
                }.distinctBy { it.id }

                val railEntities = movieRailsResponse.rails.mapIndexed { index, rail ->
                    rail.toEntity(index)
                }

                val crossRefs = movieRailsResponse.rails.flatMap { rail ->
                    rail.movies.mapIndexed { index, movie ->
                        RailMovieCrossRef(
                            railId = rail.railId,
                            movieId = movie.id,
                            orderIndex = index
                        )
                    }
                }

                movieDao.insertMovies(allMovies)
                movieRailDao.insertRails(railEntities)
                movieRailDao.insertRailMovieRefs(crossRefs)
            }
        } catch (e: Exception) {
            throw e
        }
    }


    suspend fun clearCache() {
        movieRailDao.deleteOldRails(System.currentTimeMillis())
        movieDao.deleteOldMovies(System.currentTimeMillis())
        movieRailDao.cleanupOrphanedRefs()
    }

}