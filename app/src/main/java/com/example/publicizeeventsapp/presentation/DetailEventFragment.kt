package com.example.publicizeeventsapp.presentation

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.publicizeeventsapp.R
import com.example.publicizeeventsapp.databinding.FragmentDetailEventBinding
import com.example.publicizeeventsapp.presentation.model.Event
import java.util.*


class DetailEventFragment : Fragment() {

    private lateinit var binding: FragmentDetailEventBinding
    private var event: Event? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        event = arguments?.get(EVENT_KEY_BUNDLE) as Event?
        event.let {
            setupView()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupView() {
        with(binding) {
            titleEventTextView.text = event?.title
            dateTextView.text = convertDate(event?.date)
            priceTextView.text = event?.price.toString()
            descriprionTextView.text = event?.description

            context?.let { it1 ->
                Glide
                    .with(it1)
                    .load(event?.image)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(imageEvent)
            }
            shareButton.setOnClickListener { event?.let { event -> shareEventInformation(event) } }
        }
    }

    private fun shareEventInformation(event: Event) {
        val informationEvent = mutableListOf(
            event.title,
            convertDate(event.date),
            event.price.toString(),
            event.description
        )

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, informationEvent.toString())
            type = FORMAT_INTENT_SHARE
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun convertDate(date: Long?): String = DateFormat.format(FORMAT_DATE,
        date?.let { Date(it) }).toString()

    companion object {
        private const val EVENT_KEY_BUNDLE = "event_key"
        private const val FORMAT_DATE = "dd/MM/yyyy"
        private const val FORMAT_INTENT_SHARE = "text/plain"
    }
}