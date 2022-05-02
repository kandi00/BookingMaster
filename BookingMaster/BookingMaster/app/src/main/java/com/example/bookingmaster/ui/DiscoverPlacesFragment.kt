package com.example.bookingmaster.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookingmaster.R
import com.example.bookingmaster.databinding.FragmentDiscoverPlacesBinding
import com.example.bookingmaster.viewmodel.ListViewModel

class DiscoverPlacesFragment : Fragment() {

    private var _binding: FragmentDiscoverPlacesBinding? = null
    private lateinit var place1: ImageView
    private lateinit var place2: ImageView
    private lateinit var placeEditText: EditText
    private lateinit var goPlacesButton: Button
    private lateinit var listViewModel: ListViewModel

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listViewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoverPlacesBinding.inflate(inflater, container, false)
        val fragment: View = binding.root
        initializeElements()
        setListeners()
        return fragment
    }

    private fun initializeElements() {
        place1 = binding.imageBudapest
        place2 = binding.imageDebrecen
        placeEditText = binding.etFindAccommodation
        goPlacesButton = binding.GoPlacesButton
    }

    private fun setListeners() {
        place1.setOnClickListener {
            listViewModel.accommodations = listViewModel.accommodationsBudapest
            findNavController().navigate(R.id.action_discoverPlacesFragment_to_nav_accommodation)
        }
        place2.setOnClickListener {
            listViewModel.accommodations = listViewModel.accommodationsDebrecen
            findNavController().navigate(R.id.action_discoverPlacesFragment_to_nav_accommodation)
        }
        goPlacesButton.setOnClickListener {
            try{
                listViewModel.getAccommodations(placeEditText.text.toString())
                findNavController().navigate(R.id.action_discoverPlacesFragment_to_nav_accommodation)
            } catch(e : Exception) {
                Log.d("DiscoverPlacesFragment", "Exception while getting accommodation: $e")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}