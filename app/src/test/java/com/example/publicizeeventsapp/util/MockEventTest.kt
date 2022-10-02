package com.example.publicizeeventsapp.util

import com.example.publicizeeventsapp.data.model.response.EventsResponse
import com.example.publicizeeventsapp.domain.entity.EventEntity

internal fun mockEventResponse(): EventsResponse {
    return EventsResponse(
        people = listOf(
            "Ana",
            "Rubens",
            "Theo"
        ),
        date = 1534784400000,
        description = "Evento será realizado no centro de Recife e aberto ao público.",
        image = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww2.recife.pe.gov.br%2Fsites%2Fdefault%2Ffiles%2Fstyles%2Fimagem_slide_home%2Fpublic%2Fsol.jpg%3Fitok%3DidpCkSTN&imgrefurl=https%3A%2F%2Fwww2.recife.pe.gov.br%2Fservico%2Fcidade-1&tbnid=-U5o7301h8hqXM&vet=12ahUKEwjDoP6bhsD6AhX1r5UCHTHaCdkQMygFegUIARDtAQ..i&docid=OxJa0VPRgJazhM&w=770&h=434&q=recife&ved=2ahUKEwjDoP6bhsD6AhX1r5UCHTHaCdkQMygFegUIARDtAQ",
        longitude = -34.900002,
        latitude = -8.050000,
        price = 10.00,
        title = "Festival da Gastronomia",
        id = "1"
    )
}

internal fun mockEvent(): EventEntity {
    return EventEntity(
        people = listOf(
            "Ana",
            "Rubens",
            "Theo"
        ),
        date = 1534784400000,
        description = "Evento será realizado no centro de Recife e aberto ao público.",
        image = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww2.recife.pe.gov.br%2Fsites%2Fdefault%2Ffiles%2Fstyles%2Fimagem_slide_home%2Fpublic%2Fsol.jpg%3Fitok%3DidpCkSTN&imgrefurl=https%3A%2F%2Fwww2.recife.pe.gov.br%2Fservico%2Fcidade-1&tbnid=-U5o7301h8hqXM&vet=12ahUKEwjDoP6bhsD6AhX1r5UCHTHaCdkQMygFegUIARDtAQ..i&docid=OxJa0VPRgJazhM&w=770&h=434&q=recife&ved=2ahUKEwjDoP6bhsD6AhX1r5UCHTHaCdkQMygFegUIARDtAQ",
        longitude = -34.900002,
        latitude = -8.050000,
        price = 10.00,
        title = "Festival da Gastronomia",
        id = "1"
    )
}
