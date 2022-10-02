package com.example.publicizeeventsapp.domain.usecase

import app.cash.turbine.test
import com.example.publicizeeventsapp.domain.entity.EventEntity
import com.example.publicizeeventsapp.domain.repository.EventsRepository
import com.example.publicizeeventsapp.util.mockListEvent
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import kotlin.test.assertFails
import kotlin.time.ExperimentalTime

@ExperimentalTime
internal class GetEventsUseCaseTest {

    private val repository = mockk<EventsRepository>()
    private val getEventsUseCase = GetEventsUseCase(repository)

    @Test
    fun `getEventsUseCase Should return List of EventEntity When repository is success`() =
        runBlocking {
            //Given
            val expectedResult = flow<List<EventEntity>> { mockListEvent() }
            every { repository.getEvents() } returns expectedResult

            //When
            val result = getEventsUseCase()

            //Then
            result.test {
                assertEquals(expectedResult, result)
                awaitComplete()
            }
        }

    @Test
    fun `getEventsUseCase Should throw throwable When repository is failure`() = runBlocking {
        //Given
        val throwableExpected = Throwable::class.java
        every { repository.getEvents() } throws Throwable()

        //When
        val result = assertFails { getEventsUseCase() }

        //Then
        assertEquals(throwableExpected, result::class.java)

    }
}