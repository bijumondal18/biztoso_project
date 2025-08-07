package com.app.biztosoproject.core.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.app.biztosoproject.core.extensions.doOnApplyWindowInsets
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.MaterialToolbar

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    abstract fun setupViews()

    abstract fun observeData()

    // Fragment lifecycle callbacks
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    // View lifecycle callbacks
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeData()
    }

    // Cleanup binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Utility function to navigate to an activity
    protected fun <T> goToActivity(
        activityClass: Class<T>,
        extras: Bundle? = null,
        finishCurrent: Boolean = false
    ) {
        val intent = Intent(requireContext(), activityClass)
        extras?.let { intent.putExtras(it) }
        startActivity(intent)
        if (finishCurrent) {
            activity?.finish()
        }
    }

    protected fun setupToolbar(toolbar: MaterialToolbar, title: String? = null) {
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                this.title = title
                setDisplayHomeAsUpEnabled(false)
                setDisplayShowHomeEnabled(false)
            }
        }
    }

    protected fun setupCollapsingToolbar(
        toolbar: MaterialToolbar,
        collapsingToolbarLayout: CollapsingToolbarLayout,
        title: String
    ) {
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(false)
                setDisplayShowHomeEnabled(false)
            }
        }

        collapsingToolbarLayout.title = title
    }


}