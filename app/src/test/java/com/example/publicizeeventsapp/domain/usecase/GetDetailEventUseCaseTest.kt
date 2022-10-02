package com.example.publicizeeventsapp.domain.usecase

import app.cash.turbine.test
import com.example.publicizeeventsapp.domain.entity.EventEntity
import com.example.publicizeeventsapp.domain.repository.EventsRepository
import com.example.publicizeeventsapp.util.mockEvent
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import kotlin.test.assertFails
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
internal class GetDetailEventUseCaseTest {

    private val repository = mockk<EventsRepository>()
    private val getDetailEventUseCase = GetDetailEventUseCase(repository)

    @Test
    fun `getDetailEventUseCase Should return EventEntity When repository is success`() =
        runBlocking {
            //Given
            val expectedResult = flow<EventEntity> { mockEvent() }
            every { repository.getDetailEvent("1") } returns expectedResult

            //When
            val result = getDetailEventUseCase("1")

            //Then
            result.test {
                assertEquals(expectedResult, result)
                awaitComplete()
            }
        }

    @Test
    fun `getDetailEventUseCase Should throw throwable When repository is failure`() = runBlocking {
        //Given
        val throwableExpected = Throwable::class.java
        every { repository.getDetailEvent("1") } throws Throwable()

        //When
        val result = assertFails { getDetailEventUseCase("1") }

        //Then
        assertEquals(throwableExpected, result::class.java)

    }
}