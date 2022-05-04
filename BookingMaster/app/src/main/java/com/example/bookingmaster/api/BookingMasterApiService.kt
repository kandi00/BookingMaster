package com.example.bookingmaster.api

import com.example.bookingmaster.model.AccommodationResponse
import com.example.bookingmaster.model.BookingRequest
import com.example.bookingmaster.model.BookingResponse
import com.example.bookingmaster.model.LoginRequest
import com.example.bookingmaster.model.RegistrationRequest
import retrofit2.http.GET
import com.example.bookingmaster.util.BackendConstants
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface BookingMasterApiService {
    /** get accommodations */
    @GET(BackendConstants.ACCOMMODATIONS_BY_LOCATION)
    suspend fun getAccommodationsByLocation(@Header("Authorization") token: String, @Header("location") location: String, @Header("fromDate") fromDate: String, @Header("toDate") toDate: String): AccommodationResponse

    @GET(BackendConstants.ACCOMMODATIONS_BY_NAME)
    suspend fun getAccommodationsByName(@Header("Authorization") token: String, @Header("name") name: String, @Header("fromDate") fromDate: String, @Header("toDate") toDate: String): AccommodationResponse

    /** login */
    @POST(BackendConstants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest) : String

    /** register */
    @POST(BackendConstants.REGISTRATION_URL)
    suspend fun register(@Body request: RegistrationRequest) : String
    suspend fun getAccommodationsByLocation(@Header("location") location: String): AccommodationResponse

    /** post booking */
    @POST(BackendConstants.POST_BOOKING)
    suspend fun addBooking(@Body bookingRequest : BookingRequest): BookingResponse
}