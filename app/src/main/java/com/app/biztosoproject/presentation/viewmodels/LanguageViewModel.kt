package com.app.biztosoproject.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.biztosoproject.data.api.Resource
import com.app.biztosoproject.core.utils.showDebugLog
import com.app.biztosoproject.core.utils.showErrorLog
import com.app.biztosoproject.data.models.Language
import com.app.biztosoproject.data.repositories.LanguageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val repository: LanguageRepository
) : ViewModel() {

    private val _languages = MutableLiveData<Resource<ArrayList<Language>>>()
    val languages: LiveData<Resource<ArrayList<Language>>> = _languages

    init {
        fetchLanguages()
    }

    private fun fetchLanguages() {
        viewModelScope.launch {
            _languages.postValue(Resource.Loading())
            try {
                val response = repository.getLanguageList()
                _languages.postValue(Resource.Success(response.languageList))
                showDebugLog(response.message)
            } catch (e: IOException) {
                _languages.postValue(Resource.Error("Network error: ${e.message}"))
                showErrorLog("API error: ${e.message}")
            } catch (e: HttpException) {
                _languages.postValue(Resource.Error("API error: ${e.message}"))
                showErrorLog("API error: ${e.message}")
            } catch (e: Exception) {
                _languages.postValue(Resource.Error("An error occurred: ${e.message}"))
                showErrorLog("An error occurred: ${e.message}")
            }
        }
    }

}