package io.github.fuadreza.muvi.data.response

import com.squareup.moshi.Json


data class MovieReviewsDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "results")
    val results: List<Result>,
) {
    data class Result(
        @field:Json(name = "author")
        val author: String,
        @field:Json(name = "content")
        val content: String,
        @field:Json(name = "id")
        val id: String,
        @field:Json(name = "updated_at")
        val updatedAt: String,
    )
}