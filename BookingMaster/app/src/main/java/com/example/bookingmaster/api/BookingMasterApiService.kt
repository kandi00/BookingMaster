package com.example.bookingmaster.api

import com.example.bookingmaster.model.AccommodationResponse
import retrofit2.http.GET
import com.example.bookingmaster.util.BackendConstants
import retrofit2.http.Header

interface BookingMasterApiService {
    /** get accommodations */
    @GET(BackendConstants.ACCOMMODATIONS_BY_LOCATION)
    suspend fun getAccommodationsByLocation(@Header("location") location: String): AccommodationResponse
}