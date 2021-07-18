package io.github.fuadreza.muvi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.fuadreza.muvi.data.repository.MovieRepositoryImpl
import io.github.fuadreza.muvi.domain.repository.MovieRepository

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun bindMovieRepository(repository: MovieRepositoryImpl): MovieRepository
}