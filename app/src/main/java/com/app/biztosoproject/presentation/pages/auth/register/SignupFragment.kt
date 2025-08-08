package com.app.biztosoproject.presentation.pages.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.biztosoproject.R
import com.app.biztosoproject.core.base.BaseFragment
import com.app.biztosoproject.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignupBinding {
        return FragmentSignupBinding.inflate(layoutInflater, container, false)
    }

    override fun setupViews() {

    }

    override fun observeData() {

    }

}