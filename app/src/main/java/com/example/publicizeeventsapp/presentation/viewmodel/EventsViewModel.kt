package com.example.publicizeeventsapp.presentation.viewmodel

import androidx.lifecycle.*
import com.example.publicizeeventsapp.domain.usecase.GetEventsUseCase
import com.example.publicizeeventsapp.presentation.model.Event
import com.example.publicizeeventsapp.presentation.viewmodel.state.EventState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class EventsViewModel(
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>>
        get() = _events

    private val _viewState = MutableLiveData<EventState>()
    val viewState: LiveData<EventState>
        get() = _viewState

    fun getEvents() {
        viewModelScope.launch {
            getEventsUseCase()
                .onStart { showLoading() }
                .catch { showError() }
                .onCompletion { hideLoading() }
                .collect { showEventsSuccess(listEvent = it) }
        }
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

    private fun showEventsSuccess(listEvent: List<com.example.publicizeeventsapp.domain.entity.EventEntity>) {
        _events.value = listEvent.map { eventEntity ->
            Event(eventEntity)
        }
    }
}