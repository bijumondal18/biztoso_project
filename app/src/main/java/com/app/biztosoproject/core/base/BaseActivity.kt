package com.app.biztosoproject.core.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowInsetsCompat
import com.app.biztosoproject.core.extensions.doOnApplyWindowInsets
import com.google.android.material.appbar.MaterialToolbar

abstract class BaseActivity : AppCompatActivity() {

    protected val TAG: String by lazy { this::class.java.simpleName }
    protected lateinit var mContext: Context

    // ✅ Activity Result Launcher
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Theme as per system settings
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)


        mContext = this
        Log.d(TAG, "onCreate: $TAG created")

        // ✅ Register activity result launcher
        registerResultLauncher()

        init()

//        applyWindowInsets()

        initView()
        initListeners()
        observeData()
    }

    private fun registerResultLauncher() {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleActivityResult(result)
        }
    }

    // ✅ Function to override for receiving result
    open fun handleActivityResult(result: ActivityResult) {
        // Child classes can override this
    }

    /** Called to initialize variables or config **/
    protected open fun init() {}

    /** Called to initialize views **/
    protected open fun initView() {}

    /** Called to setup listeners **/
    protected open fun initListeners() {}

    /** Called to observe ViewModel or data changes **/
    protected open fun observeData() {}

    // ✅ Navigate to another activity
    inline fun <reified T : Activity> goToActivity(finishCurrent: Boolean = false, extras: Bundle? = null) {
        val intent = Intent(this, T::class.java)
        extras?.let { intent.putExtras(it) }
        startActivity(intent)
        if (finishCurrent) finish()
    }

    // ✅ Call this to launch activity for result
    inline fun <reified T : Activity> launchActivityForResult(extras: Bundle? = null) {
        val intent = Intent(this, T::class.java)
        extras?.let { intent.putExtras(it) }
        activityResultLauncher.launch(intent)
    }


    // ✅ Handle back pressed with confirmation (optional override)
    open fun onBackPressConfirmed(): Boolean {
        // return true to consume, false to allow normal back
        return false
    }

    // ✅ Make status bar transparent
    private fun makeStatusBarTransparent() {
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.decorView.systemUiVisibility = (
                android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                )
    }
    // ✅ Handle back pressed
    override fun onBackPressed() {
        if (!onBackPressConfirmed()) {
            super.onBackPressed()
        }
    }

    // Call this from child activities to setup toolbar with back button
    protected fun setupToolbarWithBack(toolbar: MaterialToolbar, title: String? = null) {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            this.title = title
        }

        // Optional: if using toolbar navigation click listener instead of menu item
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun applyWindowInsets() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    )
        }
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        window.decorView.doOnApplyWindowInsets { view, insets, initialPadding ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom

            // Apply padding to your root view
            (view as? ViewGroup)?.let { root ->
                root.setPadding(
                    root.paddingLeft,
                    root.paddingTop,
                    root.paddingRight,
                    bottom
                )
            }
        }
    }

}