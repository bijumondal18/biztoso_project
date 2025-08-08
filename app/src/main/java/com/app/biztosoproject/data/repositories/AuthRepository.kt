package com.app.biztosoproject.data.repositories

import com.app.biztosoproject.data.api.AuthApi
import com.app.biztosoproject.data.models.LoginRequest
import com.app.biztosoproject.data.models.LoginResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApi: AuthApi
) {
    suspend fun emailLogin(
        email: String,
        password: String,
        rememberMe: Boolean,
        signupType: String,
        deviceName: String,
        deviceId: String
    ): LoginResponse {
        val request = LoginRequest(email, password, rememberMe, signupType, deviceName, deviceId)
        return authApi.emailLogin(request)
    }
}