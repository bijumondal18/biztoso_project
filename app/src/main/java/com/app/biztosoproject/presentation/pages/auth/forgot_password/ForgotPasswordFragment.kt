package com.app.biztosoproject.presentation.pages.auth.forgot_password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.biztosoproject.R
import com.app.biztosoproject.core.base.BaseFragment
import com.app.biztosoproject.databinding.FragmentForgotPasswordBinding


class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentForgotPasswordBinding {
        return FragmentForgotPasswordBinding.inflate(layoutInflater, container, false)
    }

    override fun setupViews() {

    }

    override fun observeData() {

    }

}