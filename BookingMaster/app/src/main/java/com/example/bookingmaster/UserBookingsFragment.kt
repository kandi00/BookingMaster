package com.example.bookingmaster

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingmaster.adapter.AccommodationDataAdapter
import com.example.bookingmaster.adapter.UserBookingsDataAdapter
import com.example.bookingmaster.api.BookingMasterRepository
import com.example.bookingmaster.databinding.FragmentAccommodationBinding
import com.example.bookingmaster.model.Accommodation
import com.example.bookingmaster.model.Booking
import com.example.bookingmaster.model.BookingFull
import com.example.bookingmaster.viewmodel.ListViewModel
import kotlinx.coroutines.launch


class UserBookingsFragment : Fragment(), UserBookingsDataAdapter.OnItemClickListener {


    private lateinit var fragment : View
    private lateinit var listViewModel: ListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserBookingsDataAdapter
    private lateinit var repository: BookingMasterRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listViewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_user_bookings, container, false)
        initializeView(view)


        return view
    }

    private fun initializeView(view: View?) {
        repository = BookingMasterRepository()
        if (view != null) {
            recyclerView = view.findViewById(R.id.user_bookings_recycler_view)
            recyclerView.setHasFixedSize(true)
        }
        adapter = UserBookingsDataAdapter(ArrayList<BookingFull>(), this)
        recyclerView.adapter = adapter

        listViewModel.userBookings.observe(viewLifecycleOwner){
            adapter.setData(listViewModel.userBookings.value as ArrayList<BookingFull>)
            adapter.notifyDataSetChanged()
        }

    }
    override fun onItemClick(booking: BookingFull) {

        listViewModel.userBookings.value?.remove(booking)
        try {
            lifecycleScope.launch {
                try{
                    val booking_id = booking.id

                    val response = repository.DeleteBooking(booking_id)
                    Log.d("xxx", "order accept response: $response")
                }catch(e: Exception){
                    Log.d("xxx", e.toString())
                }
            }
            //Toast.makeText(requireContext(),"decline button task delegation success: $position", Toast.LENGTH_LONG).show()

            //withEditText(requireView())
        }catch(e: java.lang.NullPointerException){
            Log.d("xxx", "on decline Button click")
        }
        adapter.notifyDataSetChanged()
    }

}