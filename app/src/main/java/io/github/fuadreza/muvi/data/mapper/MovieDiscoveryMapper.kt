package io.github.fuadreza.muvi.data.mapper

import io.github.fuadreza.core_android.abstraction.Mapper
import io.github.fuadreza.muvi.data.response.MovieDiscoveryDto
import io.github.fuadreza.muvi.domain.entity.ItemMovieDiscovery
import javax.inject.Inject

class MovieDiscoveryMapper @Inject constructor(): Mapper<MovieDiscoveryDto, List<ItemMovieDiscovery>>() {
    override fun map(input: MovieDiscoveryDto): List<ItemMovieDiscovery> {
        return input.results.map {
            ItemMovieDiscovery(
                it.id,
                it.overview,
                it.posterPath ?: "/niw2AKHz6XmwiRMLWaoyAOAti0G.jpg",
                it.backdropPath ?: "/las0P4Dua54XrZ73VQmGUaH1z0U.jpg",
                it.title
            )
        }
    }
}