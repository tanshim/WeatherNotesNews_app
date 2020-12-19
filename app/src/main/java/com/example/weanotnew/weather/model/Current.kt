package com.example.weanotnew.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Current (

    @SerializedName("dt")
    @Expose
    var dt: Int? = null,

    @SerializedName("sunrise")
    @Expose
    var sunrise: Int? = null,

    @SerializedName("sunset")
    @Expose
    var sunset: Int? = null,

    @SerializedName("temp")
    @Expose
    var temp: Double? = null,

    @SerializedName("feels_like")
    @Expose
    var feelsLike: Double? = null,

    @SerializedName("pressure")
    @Expose
    var pressure: Double? = null,

    @SerializedName("humidity")
    @Expose
    var humidity: Double? = null,

    @SerializedName("dew_point")
    @Expose
    var dewPoint: Double? = null,

    @SerializedName("uvi")
    @Expose
    var uvi: Double? = null,

    @SerializedName("clouds")
    @Expose
    var clouds: Double? = null,

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
    var weather: List<Weather>? = null

)