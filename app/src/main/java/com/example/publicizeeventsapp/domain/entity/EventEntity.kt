package com.example.publicizeeventsapp.domain.entity

data class EventEntity(
    val people: List<String>?,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Double,
    val title: String,
    val id: String
)