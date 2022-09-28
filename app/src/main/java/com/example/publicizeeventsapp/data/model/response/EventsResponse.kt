package com.example.publicizeeventsapp.data.model.response

import com.example.publicizeeventsapp.domain.entity.EventEntity
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

internal fun EventsResponse.toDomain() = EventEntity(
    people = this.people,
    date = this.date,
    description = this.description,
    image = this.image,
    longitude = this.longitude,
    latitude = this.latitude,
    price = this.price,
    title = this.title,
    id = this.id
)