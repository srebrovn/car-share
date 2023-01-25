package com.srebrov.carshare

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.srebrov.carshare.databinding.FragmentReservationBinding
import com.srebrov.carshare.utils.Car
import com.srebrov.carshare.utils.Station
import java.util.*

class ReservationFragment(val car: Car) : Fragment() {

    private var _binding: FragmentReservationBinding? = null
    private val binding get() = _binding!!
    private lateinit var app: MyApplication
//    private lateinit var auth: FirebaseAuth
    var day: Int? = null
    var month: Int? = null
    var year: Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        app = (activity?.application as MyApplication)
        _binding = FragmentReservationBinding.inflate(inflater, container, false)
//        auth = Firebase.auth

        (activity as MainActivity).supportActionBar?.title = "Reserve a car"

        val station: Station? = app.stations.single {
            it.id == car.stationId
        }
        binding.tvCarName.text = car.name
        binding.tvStationName.text = station?.name
        binding.tvStationAddress.text = station?.address?.street

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.idBtnPickDate.setOnClickListener {

            if (year == null && month == null && day == null) {
                val c = Calendar.getInstance()
                year = c.get(Calendar.YEAR)
                month = c.get(Calendar.MONTH)
                day = c.get(Calendar.DAY_OF_MONTH)
            }

            val datePickerDialog = DatePickerDialog(

                activity as MainActivity,
                { view, year, month, day ->
                    this.year = year
                    this.month = month
                    this.day = day
                    val dateStr = "${day}-${month + 1}-${year}"
                    binding.tvDate.text = dateStr
                },

                year!!,
                month!!,
                day!!
            )
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()+24*60*60*1000)
            datePickerDialog.show()
        }

//        binding.btnReserve.setOnClickListener {
//
//            val stationName = binding.tvStationName.text.toString()
//            val stationAddress = binding.tvStationAddress.text.toString()
//            val carName = binding.tvCarName.text.toString()
//
//            if(year != null && month != null && day!= null) {
//                val date = GregorianCalendar(year!!, month!! , day!!).time
//                val reservation = Reservation(carName, stationAddress, stationName, date)
//                app.database.addReservation(reservation)
//                val ref:DocumentReference = app.dbFirebase.collection("reservations").document()
//                app.dbFirebase.collection("reservations").document(ref.id).set(reservation)
//                Toast.makeText(activity as MainActivity, "Reservation added", Toast.LENGTH_SHORT).show()
//                parentFragmentManager.beginTransaction()
//                    .setCustomAnimations(
//                        R.anim.slide_in_right,
//                        R.anim.slide_out_left,
//                        R.anim.slide_in_left,
//                        R.anim.slide_out_right
//                    )
//                    .replace(R.id.nav_host_fragment_content_main, LocationsFragment())
//                    .addToBackStack(null)
//                    .commit()
//            }else {
//                Toast.makeText(activity as MainActivity, "Select date", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

}