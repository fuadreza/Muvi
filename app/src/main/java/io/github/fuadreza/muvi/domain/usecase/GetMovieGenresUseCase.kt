package io.github.fuadreza.muvi.domain.usecase

import io.github.fuadreza.core_android.abstraction.UseCase
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.domain.entity.MovieGenre
import io.github.fuadreza.muvi.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieGenresUseCase @Inject constructor(private val movieRepository: MovieRepository)
    :UseCase<UseCase.None, Results<List<MovieGenre>>>(){
    override suspend fun invoke(params: None): Results<List<MovieGenre>> {
        return movieRepository.getMoviesGenres()
    }
}