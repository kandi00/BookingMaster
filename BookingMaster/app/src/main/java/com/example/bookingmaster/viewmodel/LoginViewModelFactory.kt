package com.example.bookingmaster.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookingmaster.api.BookingMasterRepository

class LoginViewModelFactory(private val context: Context, private val repository: BookingMasterRepository) :
    ViewModelProvider.Factory {override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return LoginViewModel(context,repository) as T
}
}