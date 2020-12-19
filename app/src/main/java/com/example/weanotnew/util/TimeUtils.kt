package com.example.weanotnew.util

import java.math.RoundingMode
import java.sql.Timestamp
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun convertTimestampToTime(timestamp: Long?): String {

    if(timestamp == null) return ""
    //Get instance of calendar
    val calendar = Calendar.getInstance(Locale.getDefault())
    //get current date from timestamp
    calendar.timeInMillis = timestamp
    //return formatted date
    return android.text.format.DateFormat.format("dd.MM.yyyy HH:mm", calendar).toString()

}

fun convertTimestampToHour(timestamp: Long?, timeZone: String?): String {

    if(timestamp == null) return ""
    val calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone))
    calendar.timeInMillis = timestamp
    return android.text.format.DateFormat.format("HH:mm", calendar).toString()

}

fun convertTimestampToDayOfWeek(timestamp: Long?, timeZone: String?): String {

    if(timestamp == null) return ""
    val calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone))
    calendar.timeInMillis = timestamp
    return android.text.format.DateFormat.format("EEE", calendar).toString().capitalize(Locale.getDefault())

}

