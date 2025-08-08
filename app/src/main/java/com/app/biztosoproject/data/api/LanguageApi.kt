package com.app.biztosoproject.data.api

import com.app.biztosoproject.data.models.GetLanguage
import retrofit2.http.GET

interface LanguageApi {
    @GET("user/get_language")
    suspend fun getLanguageList(): GetLanguage

}