package io.github.fuadreza.muvi.data.mapper

import io.github.fuadreza.core_android.abstraction.Mapper
import io.github.fuadreza.muvi.data.response.MovieGenreDto
import io.github.fuadreza.muvi.domain.entity.MovieGenre
import javax.inject.Inject

class MovieGenreMapper @Inject constructor(): Mapper<MovieGenreDto, List<MovieGenre>>() {
    override fun map(input: MovieGenreDto): List<MovieGenre> {
        return input.genres.map {
            MovieGenre(
                it.id,
                it.name
            )
        }
    }
}