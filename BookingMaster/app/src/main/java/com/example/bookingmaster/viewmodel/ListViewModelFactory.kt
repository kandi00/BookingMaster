package com.example.bookingmaster.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.example.bookingmaster.api.BookingMasterRepository

class ListViewModelFactory(private val repository: BookingMasterRepository) : Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(repository) as T
    }
}