package com.example.bookingmaster.model

import com.squareup.moshi.JsonClass

/** Post booking **/
@JsonClass(generateAdapter = true)
data class BookingResponse(val booking : Booking, val message : String, val code : Int)

@JsonClass(generateAdapter = true)
data class BookingRequest(
    val from_date: String,
    var to_date: String,
    var RoomId: Int,
    val UserId: Int,
)

@JsonClass(generateAdapter = true)
data class Booking(
    var booking_date: String
)