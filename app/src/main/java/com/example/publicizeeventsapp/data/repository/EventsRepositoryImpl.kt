package com.example.publicizeeventsapp.data.repository

import com.example.publicizeeventsapp.data.datasource.remote.EventsRemoteDataSource
import com.example.publicizeeventsapp.data.mapper.EventMapper
import com.example.publicizeeventsapp.data.model.request.CheckInRequest
import com.example.publicizeeventsapp.domain.entity.EventEntity
import com.example.publicizeeventsapp.domain.repository.EventsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okhttp3.ResponseBody

internal class EventsRepositoryImpl(
    private val remoteDataSource: EventsRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val eventMapper: EventMapper
) : EventsRepository {
    override fun getEvents(): Flow<List<EventEntity>> =
        remoteDataSource.getEvents().map { event ->
            event.map(eventMapper::map)
        }
            .flowOn(dispatcher)

    override fun getDetailEvent(id: String): Flow<EventEntity> =
        remoteDataSource.getDetailEvent(id).map(eventMapper::map)
            .flowOn(dispatcher)

    override fun setCheckIn(checkIn: CheckInRequest): Flow<ResponseBody> {
        return remoteDataSource.setCheckIn(checkIn)
            .flowOn(dispatcher)
    }
}