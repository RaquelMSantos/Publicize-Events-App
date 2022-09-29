package com.example.publicizeeventsapp.data.mapper

import com.example.publicizeeventsapp.data.model.response.EventsResponse
import com.example.publicizeeventsapp.domain.entity.EventEntity
import com.example.publicizeeventsapp.presentation.util.Mapper

internal class EventMapper : Mapper<EventsResponse, EventEntity> {
    override fun map(source: EventsResponse): EventEntity {
        return EventEntity(
            people = source.people,
            date = source.date,
            description = source.description,
            image = source.image,
            longitude = source.longitude,
            latitude = source.latitude,
            price = source.price,
            title = source.title,
            id = source.id
        )
    }
}

