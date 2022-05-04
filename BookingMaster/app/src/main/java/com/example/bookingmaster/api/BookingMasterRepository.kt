package com.example.bookingmaster.api

import com.example.bookingmaster.model.*

import retrofit2.http.Body
import retrofit2.http.Header

class BookingMasterRepository {

    suspend fun getAccommodationsByLocation(@Header ("Authorization") token: String,@Header("location") location: String, @Header("fromDate") fromDate: String, @Header("toDate") toDate: String): AccommodationResponse {
        return RetrofitInstance.api.getAccommodationsByLocation(token,location, fromDate, toDate)
    }

    suspend fun getAccommodationsByName(@Header("Authorization") token: String,@Header("name") name: String, @Header("fromDate") fromDate: String, @Header("toDate") toDate: String): AccommodationResponse {
        return RetrofitInstance.api.getAccommodationsByName(token,name,fromDate,toDate)
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