package io.github.fuadreza.muvi.domain.repository

import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.domain.entity.GetMoviesDiscoveryParams
import io.github.fuadreza.muvi.domain.entity.ItemMovieDiscovery
import io.github.fuadreza.muvi.domain.entity.MovieDetail
import io.github.fuadreza.muvi.domain.entity.MovieGenre

interface MovieRepository {
    suspend fun getMoviesGenres(): Results<List<MovieGenre>>

    suspend fun getMoviesDiscoveryByGenre(genre: GetMoviesDiscoveryParams): Results<List<ItemMovieDiscovery>>

    suspend fun getMovieDetail(movieId: String): Results<MovieDetail>
}