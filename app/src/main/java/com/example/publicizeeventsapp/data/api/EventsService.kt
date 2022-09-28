package com.example.publicizeeventsapp.data.api

import com.example.publicizeeventsapp.data.model.response.EventsResponse
import retrofit2.http.GET

internal interface EventsService {
    @GET("events")
    suspend fun getEvents(): List<EventsResponse>
}