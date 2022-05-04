package com.example.bookingmaster.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookingmaster.R
import com.example.bookingmaster.api.BookingMasterRepository
import com.example.bookingmaster.databinding.FragmentHomeBinding
import com.example.bookingmaster.viewmodel.ListViewModel
import com.example.bookingmaster.viewmodel.ListViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var DiscoverPlacesButton: Button
    private lateinit var SearchForAccommodationButton: Button
    private lateinit var listViewModel: ListViewModel

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)

        val factory = ListViewModelFactory(BookingMasterRepository(), sharedPref)
        listViewModel = ViewModelProvider(requireActivity(), factory).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val fragment: View = binding.root
        initializeElements()
        setListeners()
        return fragment
    }

    private fun initializeElements() {
        DiscoverPlacesButton = binding.DiscoverPlacesButton
        SearchForAccommodationButton = binding.SearchForAccommodationButton
    }

    private fun setListeners() {
        DiscoverPlacesButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_discoverPlacesFragment)
        }
        SearchForAccommodationButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_slideshow)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}