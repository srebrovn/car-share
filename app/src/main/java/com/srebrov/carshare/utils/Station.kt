package com.srebrov.carshare.utils


import com.google.gson.annotations.SerializedName

class Station(
    @SerializedName("_id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("status") var status: String,
    @SerializedName("allPublicPlaces") var allParkingPlaces: Short,
    @SerializedName("chargers") var chargers: Short,
    @SerializedName("address") var address: Address,
    @SerializedName("geoLocation") var location: Location,
    @SerializedName("mainImageResource") var image: Image,
    @SerializedName("freeParkingPlaces") var freeParkingSpaces: Short,
    ) {

    var cars: MutableList<Car> = mutableListOf()
}