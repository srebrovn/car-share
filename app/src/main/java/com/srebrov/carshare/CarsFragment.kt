package com.srebrov.carshare

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.srebrov.carshare.databinding.FragmentCarsBinding
import com.srebrov.carshare.utils.Station

class CarFragment(private val station: Station) : Fragment() {

    private var _binding: FragmentCarsBinding? = null
    private val binding get() = _binding!!
    private lateinit var app: MyApplication
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<CarAdapter.ViewHolder>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        app = (activity?.application as MyApplication)
        _binding = FragmentCarsBinding.inflate(inflater, container, false)
//        (activity as MainActivity).supportActionBar?.hide()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvName.text = station.name
        binding.tvName.setTextColor(Color.WHITE)
        binding.tvAddress.text = station.address.street
        binding.tvAddress.setTextColor(Color.WHITE)
        val zipCity = "${station.address.zipCode} ${station.address.city}"
        binding.tvZipCity.text = zipCity
        binding.tvZipCity.setTextColor(Color.WHITE)

        Picasso.get()
            .load(station.image.href)
            .error(R.drawable.ic_ev_station)
            .fit()
            .noFade().into(binding.ivCarRecyclerView)


        layoutManager = LinearLayoutManager(context)
        binding.rvCars.layoutManager = layoutManager
        adapter = CarAdapter(station) {
            parentFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left,
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
                )
                .replace(R.id.nav_host_fragment_content_main, ReservationFragment(it))
                .addToBackStack(null)
                .commit()
        }
        binding.rvCars.adapter = adapter

        if(station.cars.isEmpty()) {
            binding.tvNoCarsAvailable.isVisible = true
        }
    }
}