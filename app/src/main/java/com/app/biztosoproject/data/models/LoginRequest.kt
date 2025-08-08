package com.app.biztosoproject.data.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email") var email: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("remember_me") var rememberMe: Boolean? = null,
    @SerializedName("signupType") var signupType: String? = null,
    @SerializedName("deviceName") var deviceName: String? = null,
    @SerializedName("deviceId") var deviceId: String? = null
)
