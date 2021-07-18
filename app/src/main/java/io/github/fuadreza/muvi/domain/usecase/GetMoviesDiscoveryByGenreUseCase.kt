package io.github.fuadreza.muvi.domain.usecase

import io.github.fuadreza.core_android.abstraction.UseCase
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.domain.entity.ItemMovieDiscovery
import io.github.fuadreza.muvi.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesDiscoveryByGenreUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    UseCase<String, Results<List<ItemMovieDiscovery>>>() {
    override suspend fun invoke(params: String): Results<List<ItemMovieDiscovery>> {
        return movieRepository.getMoviesDiscoveryByGenre(params)
    }
}