package io.github.fuadreza.muvi.data.repository

import io.github.fuadreza.core_android.data.dispatcher.DispatcherProvider
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.data.mapper.*
import io.github.fuadreza.muvi.data.source.remote.MovieRemoteDataSource
import io.github.fuadreza.muvi.domain.entity.*
import io.github.fuadreza.muvi.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class MovieRepositoryImpl @ExperimentalCoroutinesApi
@Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val remoteDataSource: MovieRemoteDataSource,
    private val movieGenreMapper: MovieGenreMapper,
    private val movieDiscoveryMapper: MovieDiscoveryMapper,
    private val movieDetailMapper: MovieDetailMapper,
    private val movieYoutubeTrailerMapper: MovieYoutubeTrailerMapper,
    private val movieReviewMapper: MovieReviewMapper
) : MovieRepository {
    override suspend fun getMoviesGenres(): Results<List<MovieGenre>> {
        val result = remoteDataSource.getMovieGenres(dispatcher.io)
        return when (result) {
            is Results.Success -> Results.Success(movieGenreMapper.map(result.data))
            is Results.Error -> Results.Error(result.cause, result.code, result.errorMessage)
            else -> Results.Error()
        }
    }

    override suspend fun getMoviesDiscoveryByGenre(params: GetMoviesDiscoveryParams): Results<List<ItemMovieDiscovery>> {
        val result = remoteDataSource.getMoviesDiscoveryByGenre(dispatcher.io, params)
        return when (result) {
            is Results.Success -> Results.Success(movieDiscoveryMapper.map(result.data))
            is Results.Error -> Results.Error(result.cause, result.code, result.errorMessage)
            else -> Results.Error()
        }
    }

    override suspend fun getMovieDetail(movieId: String): Results<MovieDetail> {
        val result = remoteDataSource.getMovieDetail(dispatcher.io, movieId)
        return when (result) {
            is Results.Success -> Results.Success(movieDetailMapper.map(result.data))
            is Results.Error -> Results.Error(result.cause, result.code, result.errorMessage)
            else -> Results.Error()
        }
    }

    override suspend fun getMovieYoutubeTrailer(movieId: String): Results<MovieYoutubeTrailer> {
        val result = remoteDataSource.getMovieVideos(dispatcher.io, movieId)
        return when (result) {
            is Results.Success -> Results.Success(movieYoutubeTrailerMapper.map(result.data))
            is Results.Error -> Results.Error(result.cause, result.code, result.errorMessage)
            else -> Results.Error()
        }
    }

    override suspend fun getMovieReviews(params: GetMovieReviewParams): Results<List<ItemMovieReview>> {
        val result = remoteDataSource.getMovieReviews(dispatcher.io, params)
        return when (result) {
            is Results.Success -> Results.Success(movieReviewMapper.map(result.data))
            is Results.Error -> Results.Error(result.cause, result.code, result.errorMessage)
            else -> Results.Error()
        }
    }
}