package com.app.biztosoproject.core.api

import com.app.biztosoproject.data.models.GetLanguage
import retrofit2.http.GET

interface ApiInterface {
    @GET("user/get_language")
    suspend fun getLanguageList(): GetLanguage

//    @POST("login")
//    suspend fun loginUser(@Body request: LoginRequest): LoginResponse
}