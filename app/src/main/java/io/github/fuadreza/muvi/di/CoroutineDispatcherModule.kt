package io.github.fuadreza.muvi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.fuadreza.core_android.data.dispatcher.CoroutineDispatcherProvider
import io.github.fuadreza.core_android.data.dispatcher.DispatcherProvider

@InstallIn(SingletonComponent::class)
@Module
interface CoroutineDispatcherModule {
    @Binds
    fun bindDispatcher(dispatcherProvider: CoroutineDispatcherProvider): DispatcherProvider
}