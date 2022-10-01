package com.example.publicizeeventsapp.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckInRequest(
    @SerialName("eventId") val eventId: String,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String
)