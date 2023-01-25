package com.srebrov.carshare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.srebrov.carshare.databinding.FragmentStationsBinding


class StationsFragment : Fragment() {

    private var _binding: FragmentStationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var app: MyApplication
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter:
            RecyclerView.Adapter<StationAdapter.ViewHolder>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        app = (activity?.application as MyApplication) // Access app object in Fragment
        _binding = FragmentStationsBinding.inflate(inflater, container, false)
        (activity as MainActivity).supportActionBar?.title = "CarShare"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        layoutManager = LinearLayoutManager(context)
        binding.rvStations.layoutManager = layoutManager
        adapter = StationAdapter(app.stations) {
            parentFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left,
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
                )
                .replace(R.id.nav_host_fragment_content_main, CarFragment(it))
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit()
        }
        binding.rvStations.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}