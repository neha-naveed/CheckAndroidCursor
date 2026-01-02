package com.example.core.extensions

fun String.isValidEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidUrl(): Boolean {
    return android.util.Patterns.WEB_URL.matcher(this).matches()
}

fun String?.orEmpty(): String = this ?: ""

