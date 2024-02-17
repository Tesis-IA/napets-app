package com.quantumcode.napets.ui.utils

import android.graphics.Color
import android.util.Log

fun String.parseColor() = try {
    if (this.contains("#")) {
        Color.parseColor(this)
    } else {
        Color.parseColor("#${this}")
    }
} catch (e: Exception) {
    Log.e("Error to trying parse color", e.message.toString())
    Color.parseColor("#717171")
}