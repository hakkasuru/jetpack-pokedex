package com.hakkasuru.jetdex.core.di

import com.hakkasuru.jetdex.api.service.PokeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun providesPokeService(
        retrofitClient: Retrofit
    ): PokeService = retrofitClient.create(PokeService::class.java)
}