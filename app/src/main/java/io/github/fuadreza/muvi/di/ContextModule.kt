package io.github.fuadreza.muvi.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ContextModule {
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

}