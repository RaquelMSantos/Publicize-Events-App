package com.example.publicizeeventsapp.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.publicizeeventsapp.presentation.model.Event

internal class EventAdapter(
    private val onClickItem: (Event) -> Unit,
): ListAdapter<Event, EventViewHolder>(EventDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder.from(parent, onClickItem) as EventViewHolder
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(currentList[position], holder.itemView.context)
    }
}