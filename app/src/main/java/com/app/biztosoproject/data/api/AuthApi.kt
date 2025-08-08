package com.app.biztosoproject.data.api

import com.app.biztosoproject.data.models.LoginRequest
import com.app.biztosoproject.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("user/emailLogin")
    suspend fun emailLogin(@Body request: LoginRequest): LoginResponse
}