package com.example.domainmovie.di

import com.example.corecommon.dispatcher.BaseDispatcherProvider
import com.example.corecommon.dispatcher.MainDispatcherProvider
import com.example.domainmovie.domain.usecase.GetPopularMovies
import com.example.domainmovie.domain.usecase.GetPopularMoviesImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    fun bindGetPopularMovies(useCase: GetPopularMoviesImpl): GetPopularMovies

    companion object {
        @Reusable
        @Provides
        fun provideDispatcher(): BaseDispatcherProvider {
            return MainDispatcherProvider()
        }
    }
}