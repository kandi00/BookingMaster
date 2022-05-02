package com.example.bookingmaster.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookingmaster.api.BookingMasterRepository
import com.example.bookingmaster.model.Accommodation
import kotlinx.coroutines.launch

class ListViewModel(private val repository: BookingMasterRepository) : ViewModel() {

    var tag = "ListViewModel"
    var accommodations: MutableLiveData<ArrayList<Accommodation>> = MutableLiveData()
    var accommodationsBudapest: MutableLiveData<ArrayList<Accommodation>> = MutableLiveData()
    var accommodationsDebrecen: MutableLiveData<ArrayList<Accommodation>> = MutableLiveData()
    var currentAccommodation: Accommodation? = null

    init {
        getDefaultAccommodations()
    }

    private fun getDefaultAccommodations() {
        viewModelScope.launch {
            try {
                var result = repository.getAccommodationsByLocation("Budapest")
                accommodations.value = result.accomodations as ArrayList<Accommodation>
                accommodationsBudapest.value = accommodations.value

                result = repository.getAccommodationsByLocation("Debrecen")
                accommodationsDebrecen.value = result.accomodations as ArrayList<Accommodation>

                Log.d(tag, "ListViewModel - #accommodations:  ${result.accomodations}")
            } catch (e: Exception) {
                Log.d(tag, "ListViewModel exception: $e")
            }
        }
    }

    fun getAccommodations(location : String) {
        viewModelScope.launch {
            try {
                var result = repository.getAccommodationsByLocation(location)
                accommodations.value = result.accomodations as ArrayList<Accommodation>
                Log.d(tag, "ListViewModel - #accommodations:  ${result.accomodations}")
            } catch (e: Exception) {
                Log.d(tag, "ListViewModel exception: $e")
            }
        }
    }

}