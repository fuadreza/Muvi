package io.github.fuadreza.muvi

import io.github.fuadreza.muvi.data.response.MovieVideosDto
import junit.framework.Assert.assertEquals
import org.junit.Test

class YoutubeFilterTest {
    @Test
    fun filterYoutubeVideos(){
        val movieVideos = listOf(
            MovieVideosDto.Result(
                "1",
                "jkBHhs",
                "Trailer 1",
                "Youtube",
                "Trailer"
            ),
            MovieVideosDto.Result(
                "2",
                "jkBHhs",
                "Trailer 1",
                "Vidio",
                "Trailer"
            ),
            MovieVideosDto.Result(
                "3",
                "jkBHhs",
                "Trailer 1",
                "Google",
                "Trailer"
            ),
        )

        val item = movieVideos.find { it.site == "Youtube" }
        assertEquals("1", item?.id ?: "0")
    }
}