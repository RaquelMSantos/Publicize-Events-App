package com.example.publicizeeventsapp.data.datasource.remote

import app.cash.turbine.test
import com.example.publicizeeventsapp.data.model.request.CheckInRequest
import com.example.publicizeeventsapp.data.retrofit.ServiceProvider
import com.example.publicizeeventsapp.util.mockEventResponse
import com.example.publicizeeventsapp.util.mockListEventsResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Test
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalSerializationApi::class)
@ExperimentalTime
internal class EventsRemoteDataSourceImplTest {

    private val serviceProvider = mockk<ServiceProvider>()
    private val remoteDataSource = EventsRemoteDataSourceImpl(serviceProvider)


    @Test
    fun `getEvents Should return List of EventsResponse When service is success`() = runBlocking {
        //Given
        val expectedResult = mockListEventsResponse()
        coEvery { serviceProvider.service.getEvents() } returns mockListEventsResponse()

        //When
        val result = remoteDataSource.getEvents()

        //Then
        result.test {
            assertEquals(expectedResult, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `getEvents Should return throwable When service is failure`() = runBlocking {
        //Given
        coEvery { serviceProvider.service.getEvents() } throws Throwable()

        //When
        val result = remoteDataSource.getEvents()

        //Then
        result.test {
            Assert.assertEquals(Throwable::class.java, awaitError()::class.java)
        }
    }

    @Test
    fun `getDetailEvent Should return EventsResponse When service is success`() = runBlocking {
        //Given
        val expectedResult = mockEventResponse()
        coEvery { serviceProvider.service.getDetailEvent("1") } returns mockEventResponse()

        //When
        val result = remoteDataSource.getDetailEvent("1")

        //Then
        result.test {
            assertEquals(expectedResult, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `getDetailEvent Should return throwable When service is failure`() = runBlocking {
        //Given
        coEvery { serviceProvider.service.getDetailEvent("1") } throws Throwable()

        //When
        val result = remoteDataSource.getDetailEvent("1")

        //Then
        result.test {
            assertEquals(Throwable::class.java, awaitError()::class.java)
        }
    }

    @Test
    fun `setCheckIn Should return throwable When service is failure`() = runBlocking {
        //Given
        coEvery {
            serviceProvider.service.setCheckIn(
                CheckInRequest(
                    "1",
                    "Amélia",
                    "amelia@gmail.com"
                )
            )
        } throws Throwable()

        //When
        val result = remoteDataSource.setCheckIn(
            CheckInRequest(
                "1",
                "Amélia",
                "amelia@gmail.com"
            )
        )

        //Then
        result.test {
            Assert.assertEquals(Throwable::class.java, awaitError()::class.java)
        }
    }
}