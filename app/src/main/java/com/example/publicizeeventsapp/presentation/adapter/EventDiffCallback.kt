package com.example.publicizeeventsapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.publicizeeventsapp.presentation.model.Event

internal class EventDiffCallback: DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean =
        newItem.id == oldItem.id

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean =
        newItem == oldItem
}