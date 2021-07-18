package io.github.fuadreza.muvi.domain.repository

import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.domain.entity.MovieGenre

interface MovieRepository {
    suspend fun getMoviesGenres(): Results<List<MovieGenre>>
}