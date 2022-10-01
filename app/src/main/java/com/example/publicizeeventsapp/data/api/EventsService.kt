package com.example.publicizeeventsapp.data.api

import com.example.publicizeeventsapp.data.model.request.CheckInRequest
import com.example.publicizeeventsapp.data.model.response.EventsResponse
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface EventsService {
    @GET("events")
    suspend fun getEvents(): List<EventsResponse>

    @GET("events/{id}")
    suspend fun getDetailEvent(@Path("id") id: String): EventsResponse

    @POST("checkin")
    suspend fun setCheckIn(@Body checkIn: CheckInRequest): ResponseBody
}