package com.example.weanotnew.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Main (

    @SerializedName("lat")
    @Expose
    var lat: Double? = null,

    @SerializedName("lon")
    @Expose
    var lon: Double? = null,

    @SerializedName("timezone")
    @Expose
    var timezone: String? = null,

    @SerializedName("timezone_offset")
    @Expose
    var timezoneOffset: Int? = null,

    @SerializedName("current")
    @Expose
    var current: Current? = null,

    @SerializedName("hourly")
    @Expose
    var hourly: List<Hourly>? = null,

    @SerializedName("daily")
    @Expose
    var daily: List<Daily>? = null
)