package com.app.biztosoproject.core.utils

import android.util.Log

fun showDebugLog(msg: String?) {
    Log.d("TAG", msg.toString())
}

fun showErrorLog(msg: String?) {
    Log.e("TAG", msg.toString())
}

fun showInfoLog(msg: String?) {
    Log.i("TAG", msg.toString())
}

fun showWarningLog(msg: String?) {
    Log.w("TAG", msg.toString())
}