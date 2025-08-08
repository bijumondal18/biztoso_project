package com.app.biztosoproject.core.extensions

import android.util.Patterns

// ✅ Check if string is a valid mobile number (basic global check)
fun String.isValidMobile(): Boolean {
    return this.length in 7..15 && this.all { it.isDigit() }
}

// ✅ Check if string is a valid email address
fun String.isValidEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.length in 6..15 && this.all { it.isDigit() }
}

// ✅ Check if string is not null, not blank, and not empty
fun String.isValid(): Boolean {
    return this.isNotBlank() && this.isNotEmpty()
}

// fun isValidEmail(email: String): Boolean {
//    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
//}
