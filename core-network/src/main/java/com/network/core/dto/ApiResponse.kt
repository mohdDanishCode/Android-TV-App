package com.network.core.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
    val is_success: Boolean? = null,
    val status_code: Int? = null,
    val data: T? = null,
    val error: ErrorBody? = null,
    val meta: Meta? = null
) {
    @JsonClass(generateAdapter = true)
    data class ErrorBody(
        val message: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Meta(
        val current_page: Int?,
        val last_page: Int?,
        val per_page: Int?,
        val total: Int?
    )
}