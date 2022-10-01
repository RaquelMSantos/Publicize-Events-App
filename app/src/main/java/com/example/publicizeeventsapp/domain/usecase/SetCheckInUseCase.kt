package com.example.publicizeeventsapp.domain.usecase

import com.example.publicizeeventsapp.data.model.request.CheckInRequest
import com.example.publicizeeventsapp.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

internal class SetCheckInUseCase(private val repository: EventsRepository) {
    operator fun invoke(checkIn: CheckInRequest): Flow<ResponseBody> =
        repository.setCheckIn(checkIn)
}