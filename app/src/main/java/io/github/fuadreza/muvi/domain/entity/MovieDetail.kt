package io.github.fuadreza.muvi.domain.entity

data class MovieDetail(
    val id: Int,
    val title: String,
    val backdropPath: String,
    val releaseDate: String,
    val voteAverage: String,
    val overview: String
)