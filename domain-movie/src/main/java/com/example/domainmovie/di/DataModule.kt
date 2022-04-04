package com.example.domainmovie.di

import com.example.domainmovie.BuildConfig
import com.example.domainmovie.data.api.TMDBApi
import com.example.domainmovie.data.datasource.RemoteDataSource
import com.example.domainmovie.data.datasource.RemoteDataSourceImpl
import com.example.domainmovie.data.dto.DateJsonAdapter
import com.example.domainmovie.data.repository.RepositoryImpl
import com.example.domainmovie.domain.repository.Repository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindRemoteDataSource(useCase: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    fun bindRepository(useCase: RepositoryImpl): Repository

    companion object {
        private const val TIME_OUT = 15L
        private const val API_KEY = "api_key"

        @Singleton
        @Provides
        fun provideMoshi(): Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(DateJsonAdapter())
            .build()

        @Singleton
        @Provides
        fun provideInterceptor() = Interceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(API_KEY, BuildConfig.TMDB_API_KEY)
                .build()

            val reqBuilder = original.newBuilder()
                .url(url)
            chain.proceed(reqBuilder.build())
        }

        @Singleton
        @Provides
        fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        @Singleton
        @Provides
        fun provideAPI(okHttpClient: OkHttpClient, moshi: Moshi): TMDBApi {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
                .create(TMDBApi::class.java)
        }
    }
}