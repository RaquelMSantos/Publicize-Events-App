package com.example.publicizeeventsapp.data.mapper

import com.example.publicizeeventsapp.data.model.response.EventsResponse
import com.example.publicizeeventsapp.domain.entity.EventEntity
import com.example.publicizeeventsapp.presentation.util.Mapper

internal class EventListMapper : Mapper<List<EventsResponse>, List<EventEntity>> {
    override fun map(source: List<EventsResponse>): List<EventEntity> {
        return source.map { event ->
            mapEvent(event)
        }
    }
}

internal class EventMapper : Mapper<EventsResponse, EventEntity> {
    override fun map(source: EventsResponse): EventEntity {
        return mapEvent(source)
    }
}

private fun mapEvent(event: EventsResponse): EventEntity {
    return EventEntity(
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

