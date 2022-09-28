package com.example.publicizeeventsapp.presentation

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.publicizeeventsapp.R
import com.example.publicizeeventsapp.databinding.FragmentEventsBinding
import com.example.publicizeeventsapp.presentation.adapter.EventAdapter
import com.example.publicizeeventsapp.presentation.viewmodel.EventsViewModel
import com.example.publicizeeventsapp.presentation.viewmodel.state.EventState
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsFragment : Fragment() {

    private val viewModel: EventsViewModel by viewModel()
    private val eventAdapter by lazy { EventAdapter() }
    private lateinit var binding: FragmentEventsBinding

    companion object {
        fun newInstance() = EventsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getEvents()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        with(binding.eventsRecyclerView) {
            adapter = eventAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            binding.eventsProgressBar.isVisible = state.isLoading
            if (state.isError) showTryAgain()
        }

        viewModel.events.observe(viewLifecycleOwner) { events ->
            eventAdapter.submitList(events)
        }
    }

    private fun showTryAgain() {
        AlertDialog.Builder(requireContext()).setMessage(R.string.error_message)
            .setPositiveButton(
                R.string.retry
            ) { _, _ -> viewModel.getEvents() }.setCancelable(false)
            .show()
    }
}