package io.github.fuadreza.muvi.data.response

import com.squareup.moshi.Json


data class MovieDiscoveryDto(
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "results")
    val results: List<Result>,
    @field:Json(name = "total_pages")
    val totalPages: Int,
    @field:Json(name = "total_results")
    val totalResults: Int
) {
    data class Result(
        @field:Json(name = "genre_ids")
        val genreIds: List<Int>,
        @field:Json(name = "id")
        val id: Int,
        @field:Json(name = "overview")
        val overview: String,
        @field:Json(name = "poster_path")
        val posterPath: String?,
        @field:Json(name = "backdrop_path")
        val backdropPath: String?,
        @field:Json(name = "title")
        val title: String,
    )
}