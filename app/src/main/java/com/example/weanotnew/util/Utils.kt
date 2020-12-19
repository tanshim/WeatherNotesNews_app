package com.example.weanotnew.util

fun tomorrowAndTempStringBuilder(dayTemp: Int?, nightTemp: Int?): String {

    return String.format("Завтра: %s° / %s°", dayTemp, nightTemp)
}

fun dayAndTempStringBuilder(timestamp: Int?, timeZone: String?, dayTemp: Int?, nightTemp: Int?): String {

    val stamp = timestamp?.toLong()?.times(1000)
    val dayOfWeek = convertTimestampToDayOfWeek(stamp, timeZone)

    return String.format("%s: %s° / %s°", dayOfWeek, dayTemp, nightTemp)
}