package com.network.core.network

import com.network.core.dto.movieResponse.MovieRailsResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val PLATFORM_ANDROID = "android"
        const val CONTENT_TYPE_JSON = "application/json"
    }


    @GET("/movies")
    suspend fun getMovies(): ApiResponse<com.network.core.dto.ApiResponse<MovieRailsResponse>>

}
