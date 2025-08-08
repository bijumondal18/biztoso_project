package com.app.biztosoproject.presentation.pages.auth

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.app.biztosoproject.R
import com.app.biztosoproject.core.base.BaseActivity
import com.app.biztosoproject.databinding.ActivityAuthBinding
import com.app.biztosoproject.databinding.ActivitySplashBinding
import com.app.biztosoproject.presentation.pages.auth.forgot_password.ForgotPasswordFragment
import com.app.biztosoproject.presentation.pages.auth.login.LoginFragment
import com.app.biztosoproject.presentation.pages.auth.register.SignupFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun init() {
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initView() {
        navigateToLogin()
    }

    override fun observeData() {

    }

    override fun initListeners() {

    }

    fun navigateToSignUp() {
        setFragment(SignupFragment())

    }

    private fun navigateToLogin() {
        setFragment(LoginFragment())
    }

    fun navigateToForgotPassword() {
        setFragment(ForgotPasswordFragment())
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,  // enter
                R.anim.slide_out_left,  // exit
                R.anim.slide_in_left,   // popEnter (when back pressed)
                R.anim.slide_out_right  // popExit (when back pressed)
            )
            .replace(R.id.fragment_container, fragment)
//            .addToBackStack(null)
            .commit()
    }
}