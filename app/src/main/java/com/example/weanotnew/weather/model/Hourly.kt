package com.example.weanotnew.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Hourly (

    @SerializedName("dt")
    @Expose
    var dt: Int? = null,

    @SerializedName("temp")
    @Expose
    var temp: Double? = null,

    @SerializedName("feels_like")
    @Expose
    var feelsLike: Double? = null,

    @SerializedName("pressure")
    @Expose
    var pressure: Int? = null,

    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null,

    @SerializedName("dew_point")
    @Expose
    var dewPoint: Double? = null,

    @SerializedName("uvi")
    @Expose
    var uvi: Double? = null,

    @SerializedName("clouds")
    @Expose
    var clouds: Int? = null,

    @SerializedName("visibility")
    @Expose
    var visibility: Int? = null,

    @SerializedName("wind_speed")
    @Expose
    var windSpeed: Double? = null,

    @SerializedName("wind_deg")
    @Expose
    var windDeg: Int? = null,

    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null,

    @SerializedName("pop")
    @Expose
    var pop: Double? = null

)