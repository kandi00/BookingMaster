package com.example.bookingmaster.model

import com.squareup.moshi.JsonClass

/** Get products - by location **/
@JsonClass(generateAdapter = true)
data class AccommodationResponse(val accomodations: List<Accommodation>)

@JsonClass(generateAdapter = true)
data class Accommodation(
    val id: Int,
    var name: String,
    var phone_number: String,
    val type: Int,
    val location: String,
    var total_Number_Of_Rooms: Int,
    var isPublished: Boolean,
    val rooms: List<Room>
)

@JsonClass(generateAdapter = true)
data class Room(
    val id: Int,
    var price: Int,
    var currency: String,
    var capacity: Int,
    val accommodationId: Int,
)