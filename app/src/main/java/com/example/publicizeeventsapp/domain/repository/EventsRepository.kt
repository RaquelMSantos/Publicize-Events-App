package com.example.publicizeeventsapp.domain.repository

import com.example.publicizeeventsapp.domain.entity.EventEntity
import kotlinx.coroutines.flow.Flow

internal interface EventsRepository {
    fun getEvents(): Flow<List<EventEntity>>
}