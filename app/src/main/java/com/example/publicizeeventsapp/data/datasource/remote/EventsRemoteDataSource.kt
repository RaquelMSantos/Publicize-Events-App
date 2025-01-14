package com.example.publicizeeventsapp.data.datasource.remote

import com.example.publicizeeventsapp.data.model.request.CheckInRequest
import com.example.publicizeeventsapp.data.model.response.EventsResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

internal interface EventsRemoteDataSource {
    fun getEvents(): Flow<List<EventsResponse>>
    fun getDetailEvent(id: String): Flow<EventsResponse>
    fun setCheckIn(checkIn: CheckInRequest): Flow<ResponseBody>
}