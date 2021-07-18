package io.github.fuadreza.muvi.data.service

import io.github.fuadreza.muvi.data.response.MovieGenreDto
import io.github.fuadreza.muvi.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/3/genre/movie/list")
    suspend fun getMovieGenres(@Query("api_key") apiKey: String = BuildConfig.API_KEY): MovieGenreDto

}