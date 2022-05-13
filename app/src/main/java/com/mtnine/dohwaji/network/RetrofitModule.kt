package com.mtnine.dohwaji.network

import com.google.gson.GsonBuilder
import com.mtnine.dohwaji.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {
    val BASE_URL = "https://a5mppwt298.execute-api.ap-northeast-2.amazonaws.com/"

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitApi(
        okHttpClient: OkHttpClient,
        gsonFactory: Converter.Factory,
    ): RetrofitApi {
        return Retrofit.Builder()
            .addConverterFactory(gsonFactory)
            .client(okHttpClient)
            .baseUrl(provideBaseUrl())
            .build()
            .create(RetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(pool: ConnectionPool): OkHttpClient {
        return OkHttpClient.Builder()
            .connectionPool(pool)
            .build()
    }

    @Provides
    @Singleton
    fun provideConnectionPool() : ConnectionPool {
        return ConnectionPool(10, 20000, TimeUnit.MICROSECONDS)
    }

    @Provides
    @Singleton
    fun provideGsonFactory(): Converter.Factory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

    @Provides
    @Singleton
    fun provideMainRepository(retrofitApi: RetrofitApi) = MainRepository(retrofitApi)
}