package com.example.bookingmaster.api

import com.example.bookingmaster.model.AccommodationResponse
import com.example.bookingmaster.model.LoginRequest
import com.example.bookingmaster.model.RegistrationRequest

import retrofit2.http.Header

class BookingMasterRepository {

    suspend fun getAccommodationsByLocation(@Header("location") location: String): AccommodationResponse {
        return RetrofitInstance.api.getAccommodationsByLocation(location)
    }

    suspend fun login(request: LoginRequest) : String {
        return RetrofitInstance.api.login(request)
    }
    suspend fun register(request: RegistrationRequest) : String {
        return RetrofitInstance.api.register(request)
    }

}