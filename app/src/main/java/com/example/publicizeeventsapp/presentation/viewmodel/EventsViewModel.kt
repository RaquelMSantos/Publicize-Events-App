package com.example.publicizeeventsapp.presentation.viewmodel

import androidx.lifecycle.*
import com.example.publicizeeventsapp.data.model.request.CheckInRequest
import com.example.publicizeeventsapp.domain.entity.EventEntity
import com.example.publicizeeventsapp.domain.usecase.GetDetailEventUseCase
import com.example.publicizeeventsapp.domain.usecase.GetEventsUseCase
import com.example.publicizeeventsapp.domain.usecase.SetCheckInUseCase
import com.example.publicizeeventsapp.presentation.model.Event
import com.example.publicizeeventsapp.presentation.viewmodel.state.EventState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class EventsViewModel(
    private val getEventsUseCase: GetEventsUseCase,
    private val getDetailEventUseCase: GetDetailEventUseCase,
    private val setCheckInUseCase: SetCheckInUseCase
) : ViewModel() {

    private val _listEvent = MutableLiveData<List<Event>>()
    val listEvent: LiveData<List<Event>>
        get() = _listEvent

    private val _viewState = MutableLiveData<EventState>()
    val viewState: LiveData<EventState>
        get() = _viewState

    private val _detailEvent = MutableLiveData<Event>()
    val detailEvent: LiveData<Event>
        get() = _detailEvent

    fun getEvents() {
        viewModelScope.launch {
            getEventsUseCase()
                .onStart { showLoading() }
                .catch { showError() }
                .onCompletion { hideLoading() }
                .collect { showEventsSuccess(listEvent = it) }
        }
    }

    fun getDetailEvent(id: String) {
        viewModelScope.launch {
            getDetailEventUseCase(id)
                .onStart { showLoading() }
                .catch { showError() }
                .onCompletion { hideLoading() }
                .collect { showDetailEventSuccess(event = it) }
        }
    }

    fun setCheckInEvent(checkIn: CheckInRequest) {
        viewModelScope.launch {
            setCheckInUseCase(checkIn)
                .catch { showErrorCheckIn() }
                .collect { checkInEventSuccess() }
        }
    }

    private fun checkInEventSuccess() {
        _viewState.value = EventState(checkInSuccess = true)
    }

    private fun showErrorCheckIn() {
        _viewState.value = EventState(checkInSuccess = false)
    }

    private fun hideLoading() {
        _viewState.value = EventState(isLoading = false)
    }

    private fun showLoading() {
        _viewState.value = EventState(isLoading = true)
    }

    private fun showError() {
        _viewState.value = EventState(isLoading = false, isError = true)
    }

    private fun showEventsSuccess(listEvent: List<EventEntity>) {
        _listEvent.value = listEvent.map { eventEntity ->
            Event(eventEntity)
        }
    }

    private fun showDetailEventSuccess(event: EventEntity) {
        _detailEvent.value = Event(event)
    }
}