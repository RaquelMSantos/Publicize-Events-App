package com.example.publicizeeventsapp.data.mapper

import com.example.publicizeeventsapp.util.mockEvent
import com.example.publicizeeventsapp.util.mockEventResponse
import org.junit.Assert.*
import org.junit.Test

internal class EventMapperTest {
    @Test
    fun `map Should return EventEntity When to put EventsResponse`() {
        // Given
        val mapper = EventMapper()

        // When
        val eventMapper = mapper.map(mockEventResponse())

        // Then
        assertEquals(eventMapper, mockEvent())
    }
}