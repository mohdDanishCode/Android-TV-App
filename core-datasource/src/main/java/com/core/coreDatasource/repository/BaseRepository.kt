package com.core.coreDatasource.repository


import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

abstract class BaseRepository() {


    fun parseErrorResponse(json: String): String {
        return ""
    }
}


/**
 * Extension function for the AppRepository to handle API calls and report errors
 */
suspend fun <T> BaseRepository.apiCall(
    methodName: String,
    apiCall: suspend () -> ApiResponse<T>
): T {
    try {
        val response = apiCall()
        return when (response) {
            is ApiResponse.Success -> response.data

            is ApiResponse.Failure.Error -> {
                val errorBody = response.errorBody?.string()
                val errorMessage = parseErrorResponse(errorBody ?: "")
                throw Exception(errorMessage)
            }

            is ApiResponse.Failure.Exception -> {
                throw Exception("Network error: ${response.message}")
            }
        }
    } catch (e: Exception) {
        // Report to Slack
        if (e !is UnknownHostException && e !is SocketTimeoutException && e !is CancellationException) {

        }

        // Rethrow the exception
        throw e
    }
}

