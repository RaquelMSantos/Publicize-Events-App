package com.example.publicizeeventsapp.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class EventsResponse(
    @SerialName("people") val people: List<String>,
    @SerialName("date") val date: Long,
    @SerialName("description") val description: String,
    @SerialName("image") val image: String,
    @SerialName("longitude") val longitude: Float,
    @SerialName("latitude") val latitude: Float,
    @SerialName("price") val price: Double,
    @SerialName("title") val title: String,
    @SerialName("id") val id: String
)