package com.app.biztosoproject.presentation.pages.splash

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.biztosoproject.R
import com.app.biztosoproject.app.MainActivity
import com.app.biztosoproject.core.base.BaseActivity
import com.app.biztosoproject.databinding.ActivitySplashBinding
import com.app.biztosoproject.presentation.pages.language_selection.LanguageSelectionActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun init() {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initView() {
        goToLanguageSelectionActivity()
    }

    override fun observeData() {

    }

    override fun initListeners() {

    }


    @OptIn(DelicateCoroutinesApi::class)
    private fun goToLanguageSelectionActivity() {
        GlobalScope.launch {
            delay(500)
            withContext(Dispatchers.Main) {
                goToActivity<LanguageSelectionActivity>(finishCurrent = true)
            }
        }
    }



}