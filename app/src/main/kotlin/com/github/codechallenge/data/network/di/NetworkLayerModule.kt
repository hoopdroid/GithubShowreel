package com.github.codechallenge.data.network.di

import com.github.codechallenge.data.network.Api
import com.github.codechallenge.data.network.DateTimeTypeMoshiAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkLayerModule {

    @Provides
    @Singleton
    @AndroidGithubUser
    fun provideIOScheduler(): String {
        return "android"
    }

    @Provides
    @Singleton
    fun provideApi(moshi: Moshi, client: OkHttpClient, @BaseUrl baseUrl: String): Api {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                addNetworkInterceptor(interceptor)
            }.build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(DateTimeTypeMoshiAdapter())
            .addLast((KotlinJsonAdapterFactory()))
            .build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String {
        return "https://api.github.com/"
    }
}

@Qualifier
internal annotation class AndroidGithubUser

@Qualifier
internal annotation class BaseUrl