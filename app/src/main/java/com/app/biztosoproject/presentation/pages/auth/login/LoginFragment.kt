package com.app.biztosoproject.presentation.pages.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.app.biztosoproject.R
import com.app.biztosoproject.core.base.BaseFragment
import com.app.biztosoproject.core.extensions.isValid
import com.app.biztosoproject.core.extensions.isValidEmail
import com.app.biztosoproject.core.extensions.isValidPassword
import com.app.biztosoproject.core.extensions.setSpringBounceClick
import com.app.biztosoproject.core.utils.DeviceUtils
import com.app.biztosoproject.data.api.Resource
import com.app.biztosoproject.databinding.FragmentLoginBinding
import com.app.biztosoproject.presentation.pages.auth.AuthActivity
import com.app.biztosoproject.presentation.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: AuthViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        clickListeners()
    }

    override fun observeData() {
        viewModel.loginResult.observe(viewLifecycleOwner) { loginResult ->
            when (loginResult) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnLogin.text = ""
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnLogin.text = "Login"
                    val response = loginResult.data
                    (activity as? AuthActivity)?.goToMainActivity()
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnLogin.text = "Login"
                    Toast.makeText(requireContext(), loginResult.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun clickListeners() {

        binding.btnLogin.setSpringBounceClick {
            val email = binding.tilEmail.editText?.text.toString().trim()
            val password = binding.tilPassword.editText?.text.toString().trim()

            viewModel.emailLogin(
                email = email,
                password = password,
                rememberMe = true,
                signupType = "3",
                deviceName = DeviceUtils.getDeviceName(),
                deviceId = DeviceUtils.getDeviceId(requireContext())
            )
        }

        binding.textForgetPassword.setSpringBounceClick {
            (activity as? AuthActivity)?.navigateToForgotPassword()
        }

        binding.textSignup.setSpringBounceClick {
            (activity as? AuthActivity)?.navigateToSignUp()
        }


    }


}