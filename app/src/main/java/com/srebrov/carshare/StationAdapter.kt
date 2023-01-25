package com.srebrov.carshare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.srebrov.carshare.databinding.StationItemBinding
import com.srebrov.carshare.utils.Station

class StationAdapter(
    private var stations: MutableList<Station>,
    private val onItemClicked: (Station) -> Unit
) : RecyclerView.Adapter<StationAdapter.ViewHolder>() {

    inner class ViewHolder(binding: StationItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemName: TextView = binding.tvName
        val itemAddress: TextView = binding.tvAddress
        val itemZipCity: TextView = binding.tvZipCity
        val itemAvailableCars: TextView = binding.tvNumCars
        val itemFreeParkingSpaces: TextView = binding.tvNumParkingSpaces
        val itemNumElectricChargers: TextView = binding.tvNumChargers
        val itemImage: ImageView = binding.ivStation
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val station = Station(
            stations[position].id,
            stations[position].name,
            stations[position].status,
            stations[position].allParkingPlaces,
            stations[position].chargers,
            stations[position].address,
            stations[position].location,
            stations[position].image,
            stations[position].freeParkingSpaces,
        )

        station.cars = stations[position].cars
        holder.itemView.setOnClickListener {
            onItemClicked(
                station
            )
        }

        holder.itemAddress.text = stations[position].address.street
        holder.itemName.text = stations[position].name
        val zipCity = "${stations[position].address.zipCode} ${stations[position].address.city}"
        holder.itemZipCity.text = zipCity
        holder.itemAvailableCars.text = station.cars.size.toString()
        holder.itemFreeParkingSpaces.text = station.freeParkingSpaces.toString()
        holder.itemNumElectricChargers.text = station.chargers.toString()
        holder.itemImage.contentDescription = stations[position].image.title
        Picasso.get()
            .load(station.image.href)
            .error(R.drawable.ic_ev_station)
            .fit()
            .noFade().into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return stations.size
    }
}

