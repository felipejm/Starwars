package com.felipe.starwars.base.network

import com.felipe.starwars.base.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @Named(LOGGING_INTERCEPTOR_NAME)
    fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named(LOGGING_INTERCEPTOR_NAME) interceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_GRAPHQL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    companion object {
        private const val LOGGING_INTERCEPTOR_NAME = "LOGGING_INTERCEPTOR_NAME"
    }
}
