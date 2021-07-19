package io.github.fuadreza.muvi.data.source.remote

import io.github.fuadreza.core_android.data.source.RemoteDataSource
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.data.response.*
import io.github.fuadreza.muvi.data.service.MovieService
import io.github.fuadreza.muvi.domain.entity.GetMovieReviewParams
import io.github.fuadreza.muvi.domain.entity.GetMoviesDiscoveryParams
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
        params: GetMoviesDiscoveryParams
    ): Results<MovieDiscoveryDto> {
        return safeApiCall(dispatcherProvider) { movieService.getMoviesDiscoveryByGenre(genres = params.genreId, page = params.page) }
    }

    suspend fun getMovieDetail(
        dispatcherProvider: CoroutineDispatcher,
        movieId: String
    ): Results<MovieDetailDto> {
        return safeApiCall(dispatcherProvider) { movieService.getMovieDetail(movieId = movieId) }
    }

    suspend fun getMovieVideos(
        dispatcherProvider: CoroutineDispatcher,
        movieId: String
    ): Results<MovieVideosDto> {
        return safeApiCall(dispatcherProvider) { movieService.getMovieVideos(movieId = movieId) }
    }

    suspend fun getMovieReviews(
        dispatcherProvider: CoroutineDispatcher,
        params: GetMovieReviewParams
    ): Results<MovieReviewsDto> {
        return safeApiCall(dispatcherProvider) { movieService.getMovieReviews(movieId = params.movieId, page = params.page) }
    }
}