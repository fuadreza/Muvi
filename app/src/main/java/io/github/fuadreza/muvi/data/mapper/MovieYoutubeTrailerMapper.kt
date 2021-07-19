package io.github.fuadreza.muvi.data.mapper

import io.github.fuadreza.core_android.abstraction.Mapper
import io.github.fuadreza.muvi.data.response.MovieVideosDto
import io.github.fuadreza.muvi.domain.entity.MovieYoutubeTrailer
import javax.inject.Inject

class MovieYoutubeTrailerMapper @Inject constructor() :
    Mapper<MovieVideosDto, MovieYoutubeTrailer>() {
    override fun map(input: MovieVideosDto): MovieYoutubeTrailer {
        val item = input.results?.find{ it.site == "YouTube" }
        return MovieYoutubeTrailer(
            item?.id ?: "",
            item?.key ?: ""
        )
    }
}