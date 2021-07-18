package io.github.fuadreza.muvi.data.repository

import io.github.fuadreza.core_android.data.dispatcher.DispatcherProvider
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.data.mapper.MovieGenreMapper
import io.github.fuadreza.muvi.data.source.remote.MovieRemoteDataSource
import io.github.fuadreza.muvi.domain.entity.MovieGenre
import io.github.fuadreza.muvi.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class MovieRepositoryImpl @ExperimentalCoroutinesApi
@Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val remoteDataSource: MovieRemoteDataSource,
    private val movieGenreMapper: MovieGenreMapper
): MovieRepository{
    override suspend fun getMoviesGenres(): Results<List<MovieGenre>> {
        val result = remoteDataSource.getMovieGenres(dispatcher.io)
        return when (result) {
            is Results.Success -> Results.Success(movieGenreMapper.map(result.data))
            is Results.Error -> Results.Error(result.cause, result.code, result.errorMessage)
            else -> Results.Error()
        }
    }
}