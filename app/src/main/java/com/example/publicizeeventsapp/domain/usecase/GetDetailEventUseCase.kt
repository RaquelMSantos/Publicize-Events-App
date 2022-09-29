package com.example.publicizeeventsapp.domain.usecase

import com.example.publicizeeventsapp.domain.entity.EventEntity
import com.example.publicizeeventsapp.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow

internal class GetDetailEventUseCase(private val repository: EventsRepository) {
    operator fun invoke(id: String): Flow<EventEntity> = repository.getDetailEvent(id)
}