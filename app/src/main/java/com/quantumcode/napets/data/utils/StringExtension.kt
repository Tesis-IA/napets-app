package com.quantumcode.napets.data.utils


fun String.validateEmail() = contains("@") && contains(".com")

fun String.validatePassword() = length >= 6
