package com.example.bookingmaster.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.example.bookingmaster.api.BookingMasterRepository

class ListViewModelFactory(private val repository: BookingMasterRepository, private val sharedPref : SharedPreferences) : Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(repository, sharedPref) as T
    }
}