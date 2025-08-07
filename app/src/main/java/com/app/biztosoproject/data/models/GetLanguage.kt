package com.app.biztosoproject.data.models

import com.google.gson.annotations.SerializedName


data class GetLanguage(
    @SerializedName("message") var message: String? = null,
    @SerializedName("result") var languageList: ArrayList<Language> = arrayListOf()
)

data class Language(
    @SerializedName("_id") var id: String? = null,
    @SerializedName("language_name") var languageName: String? = null,
    @SerializedName("language_code") var languageCode: String? = null,
    @SerializedName("align") var align: String? = null,
    @SerializedName("language_name_english") var languageNameEnglish: String? = null,
    @SerializedName("image") var image: String? = null

)