package io.github.fuadreza.muvi.data.mapper

import io.github.fuadreza.core_android.abstraction.Mapper
import io.github.fuadreza.muvi.data.response.MovieDetailDto
import io.github.fuadreza.muvi.data.response.MovieDiscoveryDto
import io.github.fuadreza.muvi.domain.entity.ItemMovieDiscovery
import io.github.fuadreza.muvi.domain.entity.MovieDetail
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() : Mapper<MovieDetailDto, MovieDetail>() {
    override fun map(input: MovieDetailDto): MovieDetail {
        return MovieDetail(
            input.id,
            input.title,
            input.backdropPath ?: "/gC9BUFiROWtaMsluGYziZ6lR4OJ.jpg",
            input.releaseDate,
            input.voteAverage.toString(),
            input.overview ?: "No overview"
        )
    }
}