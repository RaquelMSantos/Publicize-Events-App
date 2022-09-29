package com.example.publicizeeventsapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.publicizeeventsapp.presentation.model.Event
import com.bumptech.glide.Glide
import com.example.publicizeeventsapp.R
import com.example.publicizeeventsapp.databinding.ListItemEventBinding

internal class EventViewHolder(
    private val listItemEventBinding: ListItemEventBinding,
    private val onClickItem: (Event) -> Unit
) :
    RecyclerView.ViewHolder(listItemEventBinding.root) {

    fun bind(event: Event, context: Context) {
        with(listItemEventBinding) {
            titleTextView.text = event.title
            priceTextView.text = event.price.toString()
            Glide
                .with(context)
                .load(event.image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(postImageView)

            eventContainer.setOnClickListener {
                onClickItem.invoke(event)
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup, onClickItem: (Event) -> Unit): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemEventBinding.inflate(layoutInflater, parent, false)
            return EventViewHolder(binding, onClickItem)
        }
    }
}
