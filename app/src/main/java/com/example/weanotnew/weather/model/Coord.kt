package com.example.weanotnew.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Coord (

    @SerializedName("lon")
    @Expose
    var lon: Double? = null,

    @SerializedName("lat")
    @Expose
    var lat: Double? = null
)

