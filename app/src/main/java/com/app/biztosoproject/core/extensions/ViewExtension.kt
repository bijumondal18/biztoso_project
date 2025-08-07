package com.app.biztosoproject.core.extensions

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce

fun View.doOnApplyWindowInsets(
    block: (View, WindowInsetsCompat, InitialPadding) -> Unit
) {
    val initialPadding = recordInitialPaddingForView(this)
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        block(v, insets, initialPadding)
        insets
    }

    requestApplyInsetsWhenAttached()
}

data class InitialPadding(val left: Int, val top: Int, val right: Int, val bottom: Int)

fun recordInitialPaddingForView(view: View) = InitialPadding(
    view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom
)

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

fun View.setSpringBounceClick(onClick: (() -> Unit)? = null) {
    this.setOnClickListener {
        val scaleX = SpringAnimation(this, DynamicAnimation.SCALE_X, 1f).apply {
            spring.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            spring.stiffness = SpringForce.STIFFNESS_LOW
        }

        val scaleY = SpringAnimation(this, DynamicAnimation.SCALE_Y, 1f).apply {
            spring.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            spring.stiffness = SpringForce.STIFFNESS_LOW
        }

        this.scaleX = 0.98f
        this.scaleY = 0.98f

        scaleX.start()
        scaleY.start()

        onClick?.invoke()
    }
}