package com.hakkasuru.jetdex.core.di

import com.hakkasuru.jetdex.api.repository.PokeRepository
import com.hakkasuru.jetdex.api.service.PokeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesPokeRepository(pokeService: PokeService) = PokeRepository(pokeService)
}