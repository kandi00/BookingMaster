package com.example.bookingmaster.ui.accommodation

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookingmaster.R
import com.example.bookingmaster.databinding.FragmentBookBinding
import com.example.bookingmaster.model.BookingRequest
import com.example.bookingmaster.model.Room
import com.example.bookingmaster.viewmodel.ListViewModel
import com.google.android.material.datepicker.*
import com.google.android.material.datepicker.CalendarConstraints.DateValidator
import com.google.android.material.snackbar.Snackbar
import java.util.*


class BookFragment : Fragment() {
    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!
    private lateinit var fragment: View
    private lateinit var listViewModel: ListViewModel
    private var currentRoom: Room? = null
    private lateinit var fromDateText: TextView
    private lateinit var toDateText: TextView
    private lateinit var fromDateButton: Button
    private lateinit var toDateButton: Button
    private lateinit var sendButton: Button
    private lateinit var fromDate: String
    private lateinit var toDate: String
    private lateinit var errorMsg: String
    private var fromDateSelection: Long? = null
    private var toDateSelection: Long? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listViewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
        currentRoom = listViewModel.currentRoom
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBookBinding.inflate(inflater, container, false)
        fragment = binding.root
        initializeElements()
        setListeners()
        return fragment
    }

    private fun initializeElements() {
        fromDateText = binding.tvFromDate
        toDateText = binding.tvToDate
        fromDateButton = binding.buttonFromDate
        toDateButton = binding.buttonToDate
        sendButton = binding.sendButton
        fromDate = Calendar.getInstance().time.toString()
        toDate = Calendar.getInstance().time.toString()
        fromDateSelection = Calendar.getInstance().timeInMillis - 1
        toDateSelection = Calendar.getInstance().timeInMillis + 100000000000
    }

    private fun openDatePicker(textView: TextView, text: String) {
        val constraintsBuilderRange = CalendarConstraints.Builder()

        if (text == "Check-out date: ") {
            constraintsBuilderRange.setValidator(DateValidatorPointForward.from(fromDateSelection!!))
        } else {
            val dateValidatorMin: DateValidator = DateValidatorPointForward.now()
            val dateValidatorMax: DateValidator = DateValidatorPointBackward.before(toDateSelection!!)
            val listValidators = ArrayList<DateValidator>()
            listValidators.add(dateValidatorMin)
            listValidators.add(dateValidatorMax)
            val validators = CompositeDateValidator.allOf(listValidators)
            constraintsBuilderRange.setValidator(validators)
        }

        /** Disable past dates **/
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setTitleText("Select check-in date")
                .setCalendarConstraints(constraintsBuilderRange.build())
                .build()
        datePicker.show(childFragmentManager, "tag")

        datePicker.addOnPositiveButtonClickListener {
            textView.text = "$text ${datePicker.headerText}"
            if (text == "Check-in date: ") {
                fromDateSelection = datePicker.selection
                fromDate = datePicker.headerText
            } else {
                toDateSelection = datePicker.selection
                toDate = datePicker.headerText
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun setListeners() {

        fromDateButton.setOnClickListener {
            openDatePicker(fromDateText, "Check-in date: ")
        }

        toDateButton.setOnClickListener {
            openDatePicker(toDateText, "Check-out date: ")
        }

        sendButton.setOnClickListener {

            if (true) {
                errorMsg = "Your chek-out date must be after your check-in date"
                val back = AlertDialog.Builder(context)
                    .setTitle("Error")
                    .setMessage(errorMsg)
                    .setPositiveButton("Ok") { dialog, _ -> dialog.cancel() }
                    .create()
                back.show()
            } else {
                val booking = BookingRequest(
                    fromDate,
                    toDate,
                    currentRoom!!.id,
                    1
                )
                try {
                    //listViewModel.addBooking(booking)
                    Snackbar.make(binding.root, "Successful booking!", Snackbar.LENGTH_LONG)
                        .show()
                    findNavController().navigate(R.id.action_bookFragment_to_roomFragment)
                    Log.i("booking", booking.toString())
                } catch (e: Exception) {
                    Log.d("BookFragment", "Exception while adding new booking: $e")
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}