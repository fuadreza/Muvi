package io.github.fuadreza.muvi.data.source.remote

import io.github.fuadreza.core_android.data.source.RemoteDataSource
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.data.response.MovieDetailDto
import io.github.fuadreza.muvi.data.response.MovieDiscoveryDto
import io.github.fuadreza.muvi.data.response.MovieGenreDto
import io.github.fuadreza.muvi.data.service.MovieService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MovieRemoteDataSource @Inject constructor(private val movieService: MovieService) :
    RemoteDataSource() {

    suspend fun getMovieGenres(dispatcherProvider: CoroutineDispatcher): Results<MovieGenreDto> {
        return safeApiCall(dispatcherProvider) { movieService.getMovieGenres() }
    }

    suspend fun getMoviesDiscoveryByGenre(
        dispatcherProvider: CoroutineDispatcher,
        genre: String
    ): Results<MovieDiscoveryDto> {
        return safeApiCall(dispatcherProvider) { movieService.getMoviesDiscoveryByGenre(genre = genre) }
    }

    suspend fun getMovieDetail(
        dispatcherProvider: CoroutineDispatcher,
        movieId: String
    ): Results<MovieDetailDto> {
        return safeApiCall(dispatcherProvider) { movieService.getMovieDetail(movieId = movieId) }
    }
}