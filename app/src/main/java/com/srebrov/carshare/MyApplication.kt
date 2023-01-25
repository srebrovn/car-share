package com.srebrov.carshare

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.StrictMode
import android.util.Log
import com.google.gson.Gson
import com.srebrov.carshare.utils.Car
import com.srebrov.carshare.utils.Station
import okhttp3.OkHttpClient
import okhttp3.Request

class MyApplication : Application() {

    lateinit var okHttpClient: OkHttpClient

    //    lateinit var dbFirebase: FirebaseFirestore
    var stations: MutableList<Station> = mutableListOf()
    var cars: MutableList<Car> = mutableListOf()

    private val URLAvant2GoMaribor =
        "https://api.avant2go.com/api/locations?bbox=15.579643,46.512570,15.699463,46.593788" //Stations in Maribor

    override fun onCreate() {
        super.onCreate()
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder().permitAll().build()
        ) // Allow network on Main thread

//        dbFirebase = Firebase.firestore
//        sharedPref = getSharedPreferences(sharedName, Context.MODE_PRIVATE)

        okHttpClient = OkHttpClient()
        stations = getStationsAPI().toMutableList()
//        fillStationsWithCars()
    }

//    fun addID(id: String): Boolean {
//        val editor: SharedPreferences.Editor = sharedPref.edit()
//        if (!sharedPref.contains("ID")) {
//            return try {
//                editor.putString("ID", id)
//                editor.apply()
//                true
//            } catch (ex: Exception) {
//                Log.i("AddID", ex.message.toString())
//                false
//            }
//        }
//        return false
//    }

    /** okHTTP */
    fun getRequest(url: String, resource: String): String {
        val request = Request.Builder()
            .url(url + resource)
            .build()
        return try {
            val response = okHttpClient.newCall(request).execute()
            response.body!!.string()
        } catch (ex: Exception) {
            println("GET REQUEST ERROR " + ex.message)
            ""
        }
    }

    private fun getStationsAPI(): List<Station> {

        val apiResponse = getRequest(URLAvant2GoMaribor, "")
        val tmpStations = Gson().fromJson(apiResponse, Array<Station>::class.java).toList()
        for (station in tmpStations) {
            station.cars = mutableListOf()
        }
        return tmpStations
    }

//    fun fillStationsWithCars() {
//        dbFirebase.collection("cars")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    for (station in stations) {
//                        if (document.data.get("stationId") == station.id) {
//                            val name = document.data.get("name").toString()
//                            val capacity =
//                                document.data.get("capacity").toString().toInt()
//                            val registration =
//                                document.data.get("registrationNumber").toString()
//                            val dayPriceMinute =
//                                document.data.get("dayPriceMinute").toString()
//                                    .toBigDecimal()
//                            val dayPriceKilometer =
//                                document.data.get("dayPriceKilometer").toString()
//                                    .toBigDecimal()
//                            val nightPriceMinute =
//                                document.data.get("nightPriceMinute").toString()
//                                    .toBigDecimal()
//                            val nightPriceKilometer =
//                                document.data.get("nightPriceKilometer").toString()
//                                    .toBigDecimal()
//                            val stationId = document.data.get("stationId").toString()
//                            val car = Car(
//                                name,
//                                capacity,
//                                registration,
//                                dayPriceMinute,
//                                dayPriceKilometer,
//                                nightPriceMinute,
//                                nightPriceKilometer,
//                                true,
//                                stationId
//                            )
//                            station.cars.add(car)
//                        }
//                    }
//                }
//            }
//            .addOnFailureListener { ex ->
//                Log.d("FIREBASE", ex.toString())
//            }
//    }
}