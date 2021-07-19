package io.github.fuadreza.muvi.domain.usecase

import io.github.fuadreza.core_android.abstraction.UseCase
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.domain.entity.GetMoviesDiscoveryParams
import io.github.fuadreza.muvi.domain.entity.ItemMovieDiscovery
import io.github.fuadreza.muvi.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesDiscoveryByGenreUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    UseCase<GetMoviesDiscoveryParams, Results<List<ItemMovieDiscovery>>>() {
    override suspend fun invoke(params: GetMoviesDiscoveryParams): Results<List<ItemMovieDiscovery>> {
        return movieRepository.getMoviesDiscoveryByGenre(params)
    }
}