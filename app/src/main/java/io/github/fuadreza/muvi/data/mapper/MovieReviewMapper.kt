package io.github.fuadreza.muvi.data.mapper

import io.github.fuadreza.core_android.abstraction.Mapper
import io.github.fuadreza.core_android.utils.toLocalDateFormat
import io.github.fuadreza.muvi.data.response.MovieReviewsDto
import io.github.fuadreza.muvi.domain.entity.ItemMovieReview

import javax.inject.Inject

class MovieReviewMapper @Inject constructor() : Mapper<MovieReviewsDto, List<ItemMovieReview>>() {
    override fun map(input: MovieReviewsDto): List<ItemMovieReview> {
        return input.results.map {
            ItemMovieReview(
                it.id,
                it.author,
                it.content,
                it.updatedAt.toLocalDateFormat()
            )
        }
    }
}