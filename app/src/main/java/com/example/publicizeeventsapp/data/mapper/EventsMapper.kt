package com.example.publicizeeventsapp.data.mapper

import com.example.publicizeeventsapp.data.model.response.EventsResponse
import com.example.publicizeeventsapp.domain.entity.EventEntity
import com.example.publicizeeventsapp.presentation.util.Mapper

internal class EventsMapper : Mapper<List<EventsResponse>, List<EventEntity>> {
    override fun map(source: List<EventsResponse>): List<EventEntity> {
        return source.map {
            EventEntity(
                people = it.people,
                date = it.date,
                description = it.description,
                image = it.image,
                longitude = it.longitude,
                latitude = it.latitude,
                price = it.price,
                title = it.title,
                id = it.id
            )
        }
    }
}

