package com.hakkasuru.jetdex.core.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideOKClient(
        @ApplicationContext appContext: Context,
        chucker: ChuckerInterceptor
    ): OkHttpClient = OkHttpClient().newBuilder()
        .cache(Cache(appContext.cacheDir, (10 * 1024 * 1024).toLong()))
        .addNetworkInterceptor(chucker)
        .build()

    @Singleton
    @Provides
    fun providesChucker(
        @ApplicationContext appContext: Context
    ): ChuckerInterceptor = ChuckerInterceptor.Builder(appContext)
        .collector(
            ChuckerCollector(
                context = appContext,
                showNotification = true,
                retentionPeriod = RetentionManager.Period.ONE_DAY
            )
        )
        .createShortcut(true)
        .build()
}