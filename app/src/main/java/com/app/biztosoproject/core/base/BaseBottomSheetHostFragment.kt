package com.app.biztosoproject.core.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.app.biztosoproject.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
class BaseBottomSheetHostFragment : BottomSheetDialogFragment() {

    companion object {
        private const val KEY_FULLSCREEN = "KEY_FULLSCREEN"

        fun newInstance(contentFragment: Fragment, isFullScreen: Boolean = false): BaseBottomSheetHostFragment {
            val fragment = BaseBottomSheetHostFragment()
            fragment.contentFragment = contentFragment
            val args = Bundle()
            args.putBoolean(KEY_FULLSCREEN, isFullScreen)
            fragment.arguments = args
            return fragment
        }
    }

    private var contentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isFullScreen = arguments?.getBoolean(KEY_FULLSCREEN) ?: false
    }

    private var isFullScreen: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = FrameLayout(requireContext()).apply {
            id = View.generateViewId()
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        contentFragment?.let {
            childFragmentManager.beginTransaction()
                .replace(view.id, it)
                .commitNow()
        }

        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : BottomSheetDialog(requireContext(), theme) {
            override fun onAttachedToWindow() {
                super.onAttachedToWindow()
                window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if (isFullScreen) {
            val dialog = dialog ?: return
            val bottomSheet = dialog.findViewById<View>(
                com.google.android.material.R.id.design_bottom_sheet
            )
            bottomSheet?.layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT

            val behavior = com.google.android.material.bottomsheet.BottomSheetBehavior.from(bottomSheet!!)
            behavior.state = com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
            behavior.skipCollapsed = true
        }
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
}