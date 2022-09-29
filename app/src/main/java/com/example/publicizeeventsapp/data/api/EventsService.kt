package com.example.publicizeeventsapp.data.api

import com.example.publicizeeventsapp.data.model.response.EventsResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface EventsService {
    @GET("events")
    suspend fun getEvents(): List<EventsResponse>

    @GET("events/{id}")
    suspend fun getDetailEvent(@Path("id") id: String): EventsResponse
}