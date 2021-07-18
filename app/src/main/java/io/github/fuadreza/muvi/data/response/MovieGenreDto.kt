package io.github.fuadreza.muvi.data.response
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json


data class MovieGenreDto(
    @field:Json(name = "genres")
    val genres: List<Genre>
){
    data class Genre(
        @field:Json(name = "id")
        val id: Int,
        @field:Json(name = "name")
        val name: String
    )
}