package com.example.bookingmaster.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookingmaster.api.BookingMasterRepository
import com.example.bookingmaster.model.LoginRequest
import com.example.bookingmaster.model.User

class LoginViewModel(val context: Context, val repository: BookingMasterRepository) : ViewModel() {
    var token = MutableLiveData<String>()
    var user = MutableLiveData<User>()

    init{
        user.value = User()
    }

    suspend fun login() {
        val request = LoginRequest(email = user.value!!.email, password = user.value!!.password)
        try {
            val result = repository.login(request)
            token.value = result
            Log.d("xxx", "MyApplication - token:  ${result}")
        } catch (e: Exception) {
            Log.d("xxx", "LoginViewModel - exception: ${e.toString()}")
        }
    }
}