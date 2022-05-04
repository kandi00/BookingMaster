package com.example.bookingmaster.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookingmaster.api.BookingMasterRepository
import com.example.bookingmaster.model.*
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class ListViewModel(private val repository: BookingMasterRepository, private val sharedPref: SharedPreferences) : ViewModel() {

    var tag = "ListViewModel"
    var accommodations: MutableLiveData<ArrayList<Accommodation>> = MutableLiveData()
    var accommodationsBudapest: MutableLiveData<ArrayList<Accommodation>> = MutableLiveData()
    var accommodationsDebrecen: MutableLiveData<ArrayList<Accommodation>> = MutableLiveData()
    var currentAccommodation: Accommodation? = null
    var token: String
    lateinit var currentDate: String
    lateinit var currentDate_plus_1year: String
    var currentRoom: Room? = null

    var userBookings: MutableLiveData<ArrayList<BookingFull>> = MutableLiveData()

    init {

        token = "Bearer ${sharedPref.getString("token", "").toString()}"
        Log.d(tag, "ListViewModel - token: ${token}")
        getCurrectDate()
        getDefaultAccommodations()
        getUserBookings()
    }

    private fun getUserBookings() {
        viewModelScope.launch {
            try{
                var email = sharedPref.getString("email","").toString()
                var result = repository.getUserBookings(email)
                userBookings.value = result.bookingsByUser as ArrayList<BookingFull>

                Log.d(tag, "ListViewModel user bookings result: ${result}")

            }catch(e: Exception){
                Log.d(tag, "ListViewModel exception: $e")
            }
        }
    }

    private fun getDefaultAccommodations() {
        viewModelScope.launch {
            try {
                var result = repository.getAccommodationsByLocation(token = token,"Budapest", currentDate, currentDate_plus_1year)
                accommodations.value = result.accomodations as ArrayList<Accommodation>
                accommodationsBudapest.value = accommodations.value

                result = repository.getAccommodationsByLocation(token = token,"Debrecen", currentDate, currentDate_plus_1year)
                accommodationsDebrecen.value = result.accomodations as ArrayList<Accommodation>
                Log.i(currentDate, currentDate_plus_1year)

                Log.d(tag, "ListViewModel - #accommodations:  ${result.accomodations}")
            } catch (e: Exception) {
                Log.d(tag, "ListViewModel exception: $e")
            }
        }
    }

    fun getAccommodations(location : String) {
        viewModelScope.launch {
            try {
                var result = repository.getAccommodationsByLocation(token = token, location, currentDate, currentDate_plus_1year)
                accommodations.value = result.accomodations as ArrayList<Accommodation>
                Log.d(tag, "ListViewModel - #accommodations:  ${result.accomodations}")
            } catch (e: Exception) {
                Log.d(tag, "ListViewModel exception: $e")
            }
        }
    }

    fun getCurrectDate(){
        var c = Calendar.getInstance()
        var month = c.get(Calendar.MONTH).toString()
        if(month.length<2) {month = '0'+month}
        var day = c.get(Calendar.DAY_OF_MONTH).toString()
        if(day.length<2) {day = '0'+day}
        var date = c.get(Calendar.YEAR).toString() + "-" + month + "-" + day
        currentDate = date

        c = Calendar.getInstance()
        c.add(Calendar.YEAR, 1)
        month = c.get(Calendar.MONTH).toString()
        if(month.length<2) {month = '0'+month}
        day = c.get(Calendar.DAY_OF_MONTH).toString()
        if(day.length<2) {day = '0'+day}
        date = c.get(Calendar.YEAR).toString() + "-" + month + "-" + day
        currentDate_plus_1year = date
    }


    fun getAccommodations(name : String, fromDate : String, toDate : String) {
        viewModelScope.launch {
            try {

                var result = repository.getAccommodationsByName(token = token, name, fromDate, toDate)
                accommodations.value = result.accomodations as ArrayList<Accommodation>
                Log.d(tag, "ListViewModel - #accommodations:  ${result.accomodations}")
            } catch (e: Exception) {
                Log.d(tag, "ListViewModel exception: $e")
            }
        }
    }

    fun addBooking(booking: BookingRequest) {
        viewModelScope.launch {
            val result = repository.addBooking(booking)
            Log.i("result", result.toString())
        }
    }


}