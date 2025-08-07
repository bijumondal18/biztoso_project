package com.app.biztosoproject.core.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val BASE_URL = "http://16.171.250.86:7521/api/"

    // Create OkHttp client with interceptors
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS) // Connection timeout
            .readTimeout(30, TimeUnit.SECONDS)    // Read timeout
            .writeTimeout(30, TimeUnit.SECONDS)   // Write timeout
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer ${getToken()}") // Optional
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY // Log request/response
            })
            .build()
    }

    // Create Retrofit instance
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Generic function to get API service
    val apiService: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }

    // For access tokens (you can fetch from SharedPreferences or DataStore)
    private fun getToken(): String {
        // TODO: Replace with real token logic
        return "your_token_here"
    }
}