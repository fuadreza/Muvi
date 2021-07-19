package io.github.fuadreza.muvi.domain.usecase

import io.github.fuadreza.core_android.abstraction.UseCase
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.domain.entity.MovieYoutubeTrailer
import io.github.fuadreza.muvi.domain.repository.MovieRepository

import javax.inject.Inject

class GetMovieYoutubeTrailerUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    UseCase<String, Results<MovieYoutubeTrailer>>() {
    override suspend fun invoke(params: String): Results<MovieYoutubeTrailer> {
        return movieRepository.getMovieYoutubeTrailer(params)
    }
}