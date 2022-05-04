package com.example.bookingmaster.api

import com.example.bookingmaster.model.*

import retrofit2.http.Body
import retrofit2.http.Header

class BookingMasterRepository {

    suspend fun getAccommodationsByLocation(@Header ("Authorization") token: String,@Header("location") location: String): AccommodationResponse {
        return RetrofitInstance.api.getAccommodationsByLocation(token,location)
    }

    suspend fun login(request: LoginRequest) : String {
        return RetrofitInstance.api.login(request)
    }
    suspend fun register(request: RegistrationRequest) : String {
        return RetrofitInstance.api.register(request)
    }

    suspend fun addBooking(@Body bookingRequest : BookingRequest): BookingResponse{
        return RetrofitInstance.api.addBooking(bookingRequest)
    }

    suspend fun getUserBookings(@Header("email")email: String): UserBookingsResponse {
        return RetrofitInstance.api.getUserBookings(email)
    }

    suspend fun DeleteBooking(@Header("ID")ID: Int): DeleteBookingResponse {
        return RetrofitInstance.api.DeleteBooking(ID)
    }
}