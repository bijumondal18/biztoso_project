package com.app.biztosoproject.data.repositories

import com.app.biztosoproject.core.api.ApiInterface
import com.app.biztosoproject.data.models.GetLanguage
import com.app.biztosoproject.data.models.Language
import javax.inject.Inject

class LanguageRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {
    suspend fun getLanguageList(): GetLanguage {
        return apiInterface.getLanguageList()
    }
}