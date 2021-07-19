package io.github.fuadreza.muvi.data.service

import io.github.fuadreza.muvi.data.response.MovieGenreDto
import io.github.fuadreza.muvi.BuildConfig
import io.github.fuadreza.muvi.data.response.MovieDetailDto
import io.github.fuadreza.muvi.data.response.MovieDiscoveryDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("/3/genre/movie/list")
    suspend fun getMovieGenres(@Query("api_key") apiKey: String = BuildConfig.API_KEY): MovieGenreDto

    @GET("/3/discover/movie")
    suspend fun getMoviesDiscoveryByGenre(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("with_genres") genres: String,
        @Query("page") page: Int
    ): MovieDiscoveryDto

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MovieDetailDto
}