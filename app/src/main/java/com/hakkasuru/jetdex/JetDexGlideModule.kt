package com.hakkasuru.jetdex

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.io.InputStream

@GlideModule
class JetDexGlideModule : AppGlideModule() {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface GlideModuleEntryPoint {
        fun chuckerInterceptor(): ChuckerInterceptor
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)

        val hiltEntryPoint = EntryPointAccessors
            .fromApplication(context, GlideModuleEntryPoint::class.java)

        val httpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(hiltEntryPoint.chuckerInterceptor())
            .build()

        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(httpClient)
        )
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}