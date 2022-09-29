package com.example.publicizeeventsapp.domain.entity

data class EventEntity(
    val people: List<String>?,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Float,
    val latitude: Float,
    val price: Double,
    val title: String,
    val id: String
)