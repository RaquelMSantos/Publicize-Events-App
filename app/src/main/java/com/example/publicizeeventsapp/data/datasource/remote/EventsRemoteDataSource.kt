package com.example.publicizeeventsapp.data.datasource.remote

import com.example.publicizeeventsapp.data.model.response.EventsResponse
import kotlinx.coroutines.flow.Flow

internal interface EventsRemoteDataSource {
    fun getEvents(): Flow<List<EventsResponse>>
}