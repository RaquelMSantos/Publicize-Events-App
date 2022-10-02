package com.example.publicizeeventsapp.data.repository

import app.cash.turbine.test
import com.example.publicizeeventsapp.data.datasource.remote.EventsRemoteDataSource
import com.example.publicizeeventsapp.data.mapper.EventMapper
import com.example.publicizeeventsapp.util.*
import com.example.publicizeeventsapp.util.mockEvent
import com.example.publicizeeventsapp.util.mockEventResponse
import com.example.publicizeeventsapp.util.mockListEvent
import com.example.publicizeeventsapp.util.mockListEventsResponse
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
internal class EventsRepositoryImplTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()
    private val remoteDataSource = mockk<EventsRemoteDataSource>()
    private val eventMapper = mockk<EventMapper>()

    private val repository = EventsRepositoryImpl(remoteDataSource, coroutinesTestRule.testDispatcher, eventMapper)

    @Test
    fun `getDetailEvent Should return EventEntity When data source return success`() = runBlocking {
        // Given
        val expectedResult = mockEvent()
        every {
            remoteDataSource.getDetailEvent("1")
        } returns flow { emit(mockEventResponse()) }
        every {
                eventMapper.map(mockEventResponse())
        } returns mockEvent()

        // When
        val result = repository.getDetailEvent("1")

        // Then
        result.test {
            assertEquals(expectedResult, awaitItem())
            awaitComplete()
        }
    }
}