package com.example.bookingmaster.ui.accommodation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingmaster.R
import com.example.bookingmaster.adapter.RoomDataAdapter
import com.example.bookingmaster.databinding.FragmentRoomBinding
import com.example.bookingmaster.model.Accommodation
import com.example.bookingmaster.model.Room
import com.example.bookingmaster.viewmodel.ListViewModel

class RoomFragment : Fragment(), RoomDataAdapter.OnItemClickListener {
    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!
    private lateinit var fragment : View
    private lateinit var listViewModel: ListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RoomDataAdapter
    private var currentAccommodation : Accommodation? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listViewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
        currentAccommodation = listViewModel.currentAccommodation
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        fragment = binding.root
        recyclerView = binding.recyclerViewRooms
        setupRecyclerView()
        return fragment
    }

    private fun setupRecyclerView(){
        adapter = RoomDataAdapter(currentAccommodation!!.rooms as ArrayList<Room>, this)
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onBookNowButtonClick(room: Room) {
        listViewModel.currentRoom = room
        findNavController().navigate(R.id.action_roomFragment_to_bookFragment)
    }
}