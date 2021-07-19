package io.github.fuadreza.muvi.domain.usecase

import io.github.fuadreza.core_android.abstraction.UseCase
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.domain.entity.ItemMovieReview
import io.github.fuadreza.muvi.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieReviewsUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    UseCase<String, Results<List<ItemMovieReview>>>() {
    override suspend fun invoke(params: String): Results<List<ItemMovieReview>> {
        return movieRepository.getMovieReviews(params)
    }
}