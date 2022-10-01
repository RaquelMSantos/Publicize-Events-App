package com.example.publicizeeventsapp.presentation.viewmodel.state

internal data class EventState (
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val checkInSuccess: Boolean = false
)