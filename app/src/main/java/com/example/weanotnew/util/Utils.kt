package com.example.weanotnew.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun tomorrowAndTempStringBuilder(dayTemp: Int?, nightTemp: Int?): String {

    return String.format("Завтра: %s° / %s°", dayTemp, nightTemp)
}

fun dayAndTempStringBuilder(timestamp: Int?, timeZone: String?, dayTemp: Int?, nightTemp: Int?): String {

    val stamp = timestamp?.toLong()?.times(1000)
    val dayOfWeek = convertTimestampToDayOfWeek(stamp, timeZone)

    return String.format("%s: %s° / %s°", dayOfWeek, dayTemp, nightTemp)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}