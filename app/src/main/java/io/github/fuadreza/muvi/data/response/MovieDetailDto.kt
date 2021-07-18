package io.github.fuadreza.muvi.data.response

import com.squareup.moshi.Json


data class MovieDetailDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "backdrop_path")
    val backdropPath: String?,
    @field:Json(name = "release_date")
    val releaseDate: String,
    @field:Json(name = "vote_average")
    val voteAverage: Float,
    @field:Json(name = "overview")
    val overview: String?
)