package com.example.publicizeeventsapp.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.DialogFragment
import com.example.publicizeeventsapp.R
import com.example.publicizeeventsapp.databinding.FragmentCheckInDialogBinding

class CheckInDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentCheckInDialogBinding
    private lateinit var alertDialog: AlertDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentCheckInDialogBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.title_check_in)
        builder.setView(binding.root)
            .setPositiveButton(
                R.string.title_check_in
            ) { _, _ ->
                confirmCheckIn()
            }
            .setNegativeButton(
                R.string.cancel
            ) { _, _ ->
                dialog?.cancel()
            }

        alertDialog = builder.create()
        alertDialog.show()
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false

        setupListener()
        validateFields()

        return alertDialog

    }

    private fun setupListener() {
        binding.nameEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                validateFields()

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        binding.emailEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                validateFields()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    private fun confirmCheckIn() {
        Bundle().apply {
            putString(NAME_KEY_BUNDLE, binding.nameEditText.text.toString())
            putString(EMAIL_KEY_BUNDLE, binding.emailEditText.text.toString())
            parentFragmentManager.setFragmentResult(TAG_DIALOG, this)
        }
        dialog?.dismiss()
    }

    private fun validateFields() {
        with(binding) {
            val nameEmpty = nameEditText.text.toString().isEmpty()
            val emailEmpty = emailEditText.text.toString().isEmpty()

            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                nameEmpty.not() && emailEmpty.not()
        }
    }

    companion object {
        private const val NAME_KEY_BUNDLE = "name_key"
        private const val EMAIL_KEY_BUNDLE = "email_key"
        private const val TAG_DIALOG = "tag_dialog"
    }
}