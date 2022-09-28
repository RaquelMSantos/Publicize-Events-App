package com.example.publicizeeventsapp.presentation.model

import com.example.publicizeeventsapp.domain.entity.EventEntity

internal data class Event(
    val people: List<String>?,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Float,
    val latitude: Float,
    val price: Double,
    val title: String,
    val id: String
) {
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