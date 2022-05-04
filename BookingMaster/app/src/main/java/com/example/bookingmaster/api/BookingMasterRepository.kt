package com.example.bookingmaster.api

import com.example.bookingmaster.model.AccommodationResponse
import com.example.bookingmaster.model.BookingRequest
import com.example.bookingmaster.model.BookingResponse
import retrofit2.http.Body
import retrofit2.http.Header

class BookingMasterRepository {

    suspend fun getAccommodationsByLocation(@Header("location") location: String): AccommodationResponse {
        return RetrofitInstance.api.getAccommodationsByLocation(location)
    }

    suspend fun addBooking(@Body bookingRequest : BookingRequest): BookingResponse{
        return RetrofitInstance.api.addBooking(bookingRequest)
    }

}