package com.app.biztosoproject.core.di

import com.app.biztosoproject.core.session.SessionManager
import com.app.biztosoproject.data.api.AuthApi
import com.app.biztosoproject.data.api.LanguageApi
import com.app.biztosoproject.data.api.PostApi
import com.app.biztosoproject.data.api.StoryApi
import com.app.biztosoproject.data.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String = "http://16.171.250.86:7521/api/"

    @Provides
    @Singleton
    fun provideTokenInterceptor(sessionManager: SessionManager): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer ${sessionManager.getToken()}")
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(tokenInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLanguageApi(retrofit: Retrofit): LanguageApi =
        retrofit.create(LanguageApi::class.java)

    @Provides
    @Singleton
    fun providePostApi(retrofit: Retrofit): PostApi = retrofit.create(PostApi::class.java)

    @Provides
    @Singleton
    fun provideStoryApi(retrofit: Retrofit): StoryApi = retrofit.create(StoryApi::class.java)

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
}
