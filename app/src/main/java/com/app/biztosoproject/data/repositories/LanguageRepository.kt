package com.app.biztosoproject.data.repositories

import com.app.biztosoproject.data.api.LanguageApi
import com.app.biztosoproject.data.models.GetLanguage
import javax.inject.Inject

class LanguageRepository @Inject constructor(
    private val languageApi: LanguageApi
) {
    suspend fun getLanguageList(): GetLanguage = languageApi.getLanguageList()

}