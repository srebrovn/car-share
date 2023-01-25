package com.srebrov.carshare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.srebrov.carshare.databinding.FragmentMapBinding
import com.srebrov.carshare.utils.Station

class MapFragment : Fragment(), OnMapReadyCallback {
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    lateinit var app: MyApplication
    private var mMapView: MapView? = null
    private var googleMap: GoogleMap? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        app = (activity?.application as MyApplication)
        mMapView = binding.map
        mMapView!!.onCreate(savedInstanceState)
        mMapView!!.onResume()

        mMapView!!.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0
        googleMap?.clear() // Clears markers

        val mariborLatLon = LatLng(46.5547, 15.6459)
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(mariborLatLon, 13f))
        for (station in app.stations) {
            val marker = MarkerOptions()
            marker.position(LatLng(station.location.latitude, station.location.longitude))
            marker.title(station.name)
            marker.snippet("Vehicles: ${station.cars.size}, Available parking places: ${station.freeParkingSpaces}")
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            if (station.cars.isEmpty()) {
                marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            }
            googleMap?.addMarker(marker)
        }
        googleMap?.setOnInfoWindowClickListener { marker ->

            for (it in app.stations) {
                if (it.name == marker.title) {
                    val station: Station = it
                    parentFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.slide_in_right,
                            R.anim.slide_out_left,
                            R.anim.slide_in_left,
                            R.anim.slide_out_right
                        )
                        .replace(R.id.nav_host_fragment_content_main, CarFragment(station))
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mMapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView?.onDestroy()
        _binding = null
    }

    override fun onLowMemory() {
        mMapView?.onLowMemory()
        super.onLowMemory()
    }
}