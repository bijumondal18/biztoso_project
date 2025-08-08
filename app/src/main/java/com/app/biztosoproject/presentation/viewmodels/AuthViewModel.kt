package com.app.biztosoproject.presentation.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.biztosoproject.core.extensions.isValid
import com.app.biztosoproject.core.session.SessionManager
import com.app.biztosoproject.data.api.Resource
import com.app.biztosoproject.data.models.LoginResponse
import com.app.biztosoproject.data.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _loginResult = MutableLiveData<Resource<LoginResponse>>()
    val loginResult: LiveData<Resource<LoginResponse>> = _loginResult

    fun emailLogin(
        email: String,
        password: String,
        rememberMe: Boolean,
        signupType: String,
        deviceName: String,
        deviceId: String
    ) {
        viewModelScope.launch {
            try {
                val response = authRepository.emailLogin(
                    email,
                    password,
                    rememberMe,
                    signupType,
                    deviceName,
                    deviceId
                )
                if (response.user != null && response.token != null && response.token?.isNotEmpty() == true) {
                    sessionManager.saveToken(response.token!!)
                    sessionManager.setLoggedIn(true)
                    _loginResult.postValue(Resource.Success(response))
                } else {
                    _loginResult.postValue(Resource.Error("Invalid credentials"))
                }
            } catch (e: IOException) {
                _loginResult.postValue(Resource.Error("Network error: ${e.message}"))
            } catch (e: HttpException) {
                _loginResult.postValue(Resource.Error("Server error: ${e.message}"))
            } catch (e: Exception) {
                _loginResult.postValue(Resource.Error("Unexpected error: ${e.message}"))
            }
        }
    }
}