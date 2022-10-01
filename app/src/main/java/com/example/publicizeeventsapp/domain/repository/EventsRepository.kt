package com.example.publicizeeventsapp.domain.repository

import com.example.publicizeeventsapp.data.model.request.CheckInRequest
import com.example.publicizeeventsapp.domain.entity.EventEntity
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

internal interface EventsRepository {
    fun getEvents(): Flow<List<EventEntity>>
    fun getDetailEvent(id: String): Flow<EventEntity>
    fun setCheckIn(checkIn: CheckInRequest): Flow<ResponseBody>
}