package com.app.biztosoproject.presentation.pages.language_selection

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.biztosoproject.R
import com.app.biztosoproject.app.MainActivity
import com.app.biztosoproject.core.api.Resource
import com.app.biztosoproject.core.base.BaseActivity
import com.app.biztosoproject.core.extensions.setSpringBounceClick
import com.app.biztosoproject.core.utils.showDebugLog
import com.app.biztosoproject.data.models.Language
import com.app.biztosoproject.databinding.ActivityLanguageSelectionBinding
import com.app.biztosoproject.databinding.ActivitySplashBinding
import com.app.biztosoproject.presentation.adapters.LanguageListAdapter
import com.app.biztosoproject.presentation.viewmodels.LanguageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LanguageSelectionActivity : BaseActivity() {

    private lateinit var binding: ActivityLanguageSelectionBinding
    private lateinit var languageAdapter: LanguageListAdapter
    private val languageViewModel: LanguageViewModel by viewModels()

    override fun init() {
        binding = ActivityLanguageSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initView() {
        languageAdapter = LanguageListAdapter(binding.languageRecyclerview) { position ->
        }
//        languageViewModel = ViewModelProvider(this)[LanguageViewModel::class.java]

        configureLanguageRecyclerView()

    }

    override fun observeData() {
        languageViewModel.languages.observe(this) { state ->
            when (state) {
                is Resource.Loading -> {
                    binding.shimmer.isVisible = true
                    binding.shimmer.startShimmer()
                    binding.languageRecyclerview.isVisible = false
                }

                is Resource.Success -> {
                    binding.shimmer.isVisible = false
                    binding.shimmer.stopShimmer()
                    binding.languageRecyclerview.isVisible = true

                    val languages = state.data
                    languageAdapter.submitList(languages)
                }

                is Resource.Error -> {
                    binding.shimmer.isVisible = false
                    binding.shimmer.stopShimmer()
                }
            }

        }

    }

    override fun initListeners() {
        binding.btnContinue.setSpringBounceClick {
            val selectedLanguage = languageAdapter.getSelectedLanguage()
            if (selectedLanguage != null) {
                showDebugLog("Selected language: ${selectedLanguage.languageName}")
                goToMainActivity()
            } else {
                showDebugLog("Please select a language")
            }
        }

    }

    private fun configureLanguageRecyclerView() {
        binding.languageRecyclerview.apply {
            layoutManager = LinearLayoutManager(
                this@LanguageSelectionActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = languageAdapter
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun goToMainActivity() {
        GlobalScope.launch {
            delay(500)
            withContext(Dispatchers.Main) {
                goToActivity<MainActivity>(finishCurrent = true)
            }
        }
    }


}