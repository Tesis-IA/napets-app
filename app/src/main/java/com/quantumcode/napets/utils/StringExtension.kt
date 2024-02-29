package com.quantumcode.napets.utils

import android.graphics.Color
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

fun String.formattedDate(): Date? {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    return formatter.parse(this)
}

fun String.validateEmail() = contains("@") && contains(".com")

fun String.validatePassword() = length >= 6
