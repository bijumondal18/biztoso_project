package com.app.biztosoproject.data.models

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("message") var message: String? = null,
    @SerializedName("token") var token: String? = null,
    @SerializedName("user") var user: User? = User()
)
