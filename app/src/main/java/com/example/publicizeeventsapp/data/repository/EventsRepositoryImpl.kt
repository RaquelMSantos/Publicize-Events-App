package com.example.publicizeeventsapp.data.repository

import com.example.publicizeeventsapp.data.datasource.remote.EventsRemoteDataSource
import com.example.publicizeeventsapp.data.mapper.EventsMapper
import com.example.publicizeeventsapp.domain.entity.EventEntity
import com.example.publicizeeventsapp.domain.repository.EventsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class EventsRepositoryImpl(
    private val remoteDataSource: EventsRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val eventsMapper: EventsMapper
) : EventsRepository {
    override fun getEvents(): Flow<List<EventEntity>> =
        remoteDataSource.getEvents().map(eventsMapper::map)
            .flowOn(dispatcher)
}