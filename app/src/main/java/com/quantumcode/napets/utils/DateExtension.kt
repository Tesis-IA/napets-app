package com.quantumcode.napets.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.getFormattedDate(inputFormat: String): String = SimpleDateFormat(inputFormat, Locale("es", "ES")).format(this)
