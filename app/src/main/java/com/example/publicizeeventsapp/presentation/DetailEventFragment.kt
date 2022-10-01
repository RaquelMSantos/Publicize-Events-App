package com.example.publicizeeventsapp.presentation

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.publicizeeventsapp.R
import com.example.publicizeeventsapp.data.model.request.CheckInRequest
import com.example.publicizeeventsapp.databinding.FragmentDetailEventBinding
import com.example.publicizeeventsapp.presentation.model.Event
import com.example.publicizeeventsapp.presentation.viewmodel.EventsViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DetailEventFragment : Fragment() {

    private lateinit var binding: FragmentDetailEventBinding
    private val viewModel: EventsViewModel by viewModel()
    private lateinit var idEvent: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idEvent = arguments?.getString(EVENT_KEY_BUNDLE) as String
        idEvent.let { idEvent ->
            viewModel.getDetailEvent(idEvent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            binding.detailEventProgressBar.isVisible = state.isLoading
            binding.detailGroup.isVisible = state.isLoading.not()
            if (state.isError) showSnackBar()
            if (state.checkInSuccess) showToast()
        }

        viewModel.detailEvent.observe(viewLifecycleOwner) { detailEvent ->
            setupView(detailEvent)
        }

        parentFragmentManager.setFragmentResultListener(
            TAG_DIALOG,
            viewLifecycleOwner
        ) { _, bundle ->
            val checkIn = CheckInRequest(
                eventId = idEvent,
                name = bundle.getString(NAME_KEY_BUNDLE) ?: EMPTY_STRING,
                email = bundle.getString(EMAIL_KEY_BUNDLE) ?: EMPTY_STRING
            )
            viewModel.setCheckInEvent(checkIn)
        }
    }

    private fun showToast() {
        Toast.makeText(context, R.string.check_in_success, Toast.LENGTH_LONG).show()
    }

    private fun showSnackBar() {
        Snackbar.make(binding.detailContainer, R.string.error_detail_message, Snackbar.LENGTH_LONG)
            .setAction(R.string.retry) {
                viewModel.getDetailEvent(idEvent)
            }
            .show()
    }

    private fun setupView(detailEvent: Event) {
        with(binding) {
            titleEventTextView.text = detailEvent.title
            dateTextView.text = convertDate(detailEvent.date)
            priceTextView.text = detailEvent.price.toString()
            descriprionTextView.text = detailEvent.description

            context?.let { it1 ->
                Glide
                    .with(it1)
                    .load(detailEvent.image)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(imageEvent)
            }
            shareButton.setOnClickListener { shareEventInformation(detailEvent) }
            checkInButton.setOnClickListener {
                CheckInDialogFragment().show(parentFragmentManager, TAG_DIALOG)
            }
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
        private const val TAG_DIALOG = "tag_dialog"
        private const val NAME_KEY_BUNDLE = "name_key"
        private const val EMAIL_KEY_BUNDLE = "email_key"
        private const val FORMAT_DATE = "dd/MM/yyyy"
        private const val FORMAT_INTENT_SHARE = "text/plain"
        private const val EMPTY_STRING = ""
    }
}