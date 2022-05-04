package com.example.bookingmaster.ui.accommodation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingmaster.R
import com.example.bookingmaster.adapter.AccommodationDataAdapter
import com.example.bookingmaster.databinding.FragmentAccommodationBinding
import com.example.bookingmaster.model.Accommodation
import com.example.bookingmaster.viewmodel.ListViewModel

class AccommodationsFragment : Fragment(),  AccommodationDataAdapter.OnItemClickListener{
    private var _binding: FragmentAccommodationBinding? = null
    private val binding get() = _binding!!
    private lateinit var fragment : View
    private lateinit var listViewModel: ListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AccommodationDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listViewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAccommodationBinding.inflate(inflater, container, false)
        fragment = binding.root
        recyclerView = binding.recyclerView
        setupRecyclerView()
        listViewModel.accommodations.observe(viewLifecycleOwner){
            adapter.setData(listViewModel.accommodations.value as ArrayList<Accommodation>)
            adapter.notifyDataSetChanged()
        }
        return fragment
    }

    private fun setupRecyclerView(){
        adapter = AccommodationDataAdapter(ArrayList<Accommodation>(), this)
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(accommodation: Accommodation) {
        listViewModel.currentAccommodation = accommodation
        findNavController().navigate(R.id.action_nav_gallery_to_roomFragment)
    }
}