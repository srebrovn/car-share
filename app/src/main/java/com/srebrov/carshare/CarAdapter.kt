package com.srebrov.carshare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.srebrov.carshare.databinding.CarItemBinding
import com.srebrov.carshare.utils.Car
import com.srebrov.carshare.utils.Station

class CarAdapter(private var station: Station, private val onItemClicked: (Car) -> Unit) :
    RecyclerView.Adapter<CarAdapter.ViewHolder>() {
    inner class ViewHolder(binding: CarItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val itemCarName: TextView = binding.tvCarName
        val itemCarRegistration: TextView = binding.tvCarRegistration
        val itemDayPrice: TextView = binding.tvDayPrice
        val itemNightPrice: TextView = binding.tvNightPrice

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car: Car = station.cars[position]
        holder.itemCarName.text = car.name
        holder.itemCarRegistration.text = car.registrationNumber
        val dayPrice = "${car.dayPriceMinute} €/min, ${car.dayPriceKilometer} €/km"
        val nightPrice = "${car.nightPriceMinute} €/min, ${car.nightPriceKilometer} €/km"
        holder.itemDayPrice.text = dayPrice
        holder.itemNightPrice.text = nightPrice
        holder.itemView.setOnClickListener {
            onItemClicked(
                car
            )
        }
    }

    override fun getItemCount(): Int {
        return station.cars.size
    }
}