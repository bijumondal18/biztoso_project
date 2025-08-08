package com.app.biztosoproject.presentation.pages.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.biztosoproject.R
import com.app.biztosoproject.core.base.BaseFragment
import com.app.biztosoproject.core.extensions.setSpringBounceClick
import com.app.biztosoproject.databinding.FragmentLoginBinding
import com.app.biztosoproject.presentation.pages.auth.AuthActivity

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        clickListeners()
    }

    override fun observeData() {

    }


    private fun clickListeners() {
        binding.textForgetPassword.setSpringBounceClick {
            (activity as? AuthActivity)?.navigateToForgotPassword()
        }

        binding.textSignup.setSpringBounceClick {
            (activity as? AuthActivity)?.navigateToSignUp()
        }
    }


}