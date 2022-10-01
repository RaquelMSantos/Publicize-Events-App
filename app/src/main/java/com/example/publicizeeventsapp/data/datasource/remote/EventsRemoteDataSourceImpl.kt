package com.example.publicizeeventsapp.data.datasource.remote

import com.example.publicizeeventsapp.data.datasource.error.parseError
import com.example.publicizeeventsapp.data.model.request.CheckInRequest
import com.example.publicizeeventsapp.data.model.response.EventsResponse
import com.example.publicizeeventsapp.data.retrofit.ServiceProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.ResponseBody

@OptIn(ExperimentalSerializationApi::class)
internal class EventsRemoteDataSourceImpl(private val serviceProvider: ServiceProvider) :
    EventsRemoteDataSource {
    override fun getEvents(): Flow<List<EventsResponse>> {
        return flow { emit(serviceProvider.service.getEvents()) }.parseError()
    }

    override fun getDetailEvent(id: String): Flow<EventsResponse> {
        return flow { emit(serviceProvider.service.getDetailEvent(id)) }.parseError()
    }

    override fun setCheckIn(checkIn: CheckInRequest): Flow<ResponseBody> {
        return flow { emit(serviceProvider.service.setCheckIn(checkIn)) }.parseError()
    }
}