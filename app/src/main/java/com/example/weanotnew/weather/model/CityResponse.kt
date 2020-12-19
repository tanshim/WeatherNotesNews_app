package com.example.weanotnew.weather.model

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

data class CityResponse (

    @SerializedName("coord")
    @Expose
    var coord: Coord? = null
)