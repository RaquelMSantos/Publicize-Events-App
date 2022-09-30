package com.example.publicizeeventsapp.presentation.model

import com.example.publicizeeventsapp.domain.entity.EventEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    @SerialName("people") val people: List<String>?,
    @SerialName("date") val date: Long,
    @SerialName("description") val description: String,
    @SerialName("image") val image: String,
    @SerialName("longitude") val longitude: Float,
    @SerialName("latitude") val latitude: Float,
    @SerialName("price") val price: Double,
    @SerialName("title") val title: String,
    @SerialName("id") val id: String
) : java.io.Serializable {
    constructor(event: EventEntity) : this(
        people = event.people,
        date = event.date,
        description = event.description,
        image = event.image,
        longitude = event.longitude,
        latitude = event.latitude,
        price = event.price,
        title = event.title,
        id = event.id
    )
}