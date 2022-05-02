package com.example.bookingmaster.api

import com.example.bookingmaster.model.AccommodationResponse
import retrofit2.http.Header

class BookingMasterRepository {

    suspend fun getAccommodationsByLocation(@Header("location") location: String): AccommodationResponse {
        return RetrofitInstance.api.getAccommodationsByLocation(location)
    }

}