package com.srebrov.carshare.utils

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("country") val country: String,
    @SerializedName("city") val city: String,
    @SerializedName("zipCode") val zipCode: String,
    @SerializedName("address1") val street: String
)