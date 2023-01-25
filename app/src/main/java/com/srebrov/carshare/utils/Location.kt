package com.srebrov.carshare.utils

import com.google.gson.annotations.SerializedName

data class Location (
    @SerializedName("lng") val longitude: Double,
    @SerializedName("lat") val latitude: Double
)