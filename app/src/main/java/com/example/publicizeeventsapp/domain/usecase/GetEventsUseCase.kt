package com.example.publicizeeventsapp.domain.usecase

import com.example.publicizeeventsapp.domain.entity.EventEntity
import com.example.publicizeeventsapp.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow

internal class GetEventsUseCase(private val repository: EventsRepository) {
    operator fun invoke(): Flow<List<EventEntity>> = repository.getEvents()
}