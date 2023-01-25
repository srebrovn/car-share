package com.srebrov.carshare.utils

import java.math.BigDecimal

data class Car(
    var name: String,
    val capacity: Int,
    val registrationNumber: String,
    val dayPriceMinute: BigDecimal,
    val dayPriceKilometer: BigDecimal,
    val nightPriceMinute: BigDecimal,
    val nightPriceKilometer: BigDecimal,
    val available: Boolean,
    val stationId: String
)