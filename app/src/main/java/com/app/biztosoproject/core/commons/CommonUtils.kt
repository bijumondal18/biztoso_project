package com.app.biztosoproject.core.commons

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat

fun setClickableText(
    context: Context,
    textView: TextView,
    fullText: String,
    clickableParts: List<Triple<String, Boolean, () -> Unit>>, // (text, isBold, onClick)
    linkColorResId: Int
) {
    val spannable = SpannableString(fullText)

    for ((part, isBold, action) in clickableParts) {
        val start = fullText.indexOf(part)
        if (start < 0) continue

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) = action()

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(context, linkColorResId)
                ds.isUnderlineText = false
            }
        }

        spannable.setSpan(clickableSpan, start, start + part.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        if (isBold) {
            spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                start,
                start + part.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

    textView.text = spannable
    textView.movementMethod = LinkMovementMethod.getInstance()
    textView.highlightColor = android.graphics.Color.TRANSPARENT
}