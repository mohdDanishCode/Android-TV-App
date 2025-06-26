package com.network.core.di

import com.network.core.network.ApiService
import com.network.core.network.MockApiService
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

/**
 * Main entry point for network access.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
     * full Kotlin compatibility.
     */
    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .build()

    @AppApi
    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor {
            Timber.d(it)
        }
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }




    @AppApi
    @Singleton
    @Provides
    fun providesBaseUrl(): String {
        return ""
    }

    @AppApi
    @Singleton
    @Provides
    fun provideOkHttpClient(
        @AppApi loggingInterceptor: HttpLoggingInterceptor,

    ): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.addInterceptor(loggingInterceptor)
        return okHttpClient.build()
    }

    // Configure retrofit to parse JSON using moshi
    @AppApi
    @Singleton
    @Provides
    fun provideRetrofit(@AppApi baseUrl: String, @AppApi okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()


//    @Singleton
//    @Provides
//    fun provideApiService(@AppApi retrofit: Retrofit): ApiService =
//        retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return MockApiService()
    }
}
