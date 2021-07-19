package io.github.fuadreza.muvi.data.response
import com.squareup.moshi.Json


data class MovieVideosDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "results")
    val results: List<Result>?
) {
    data class Result(
        @field:Json(name = "id")
        val id: String,
        @field:Json(name = "key")
        val key: String,
        @field:Json(name = "name")
        val name: String,
        @field:Json(name = "site")
        val site: String,
        @field:Json(name = "type")
        val type: String
    )
}

