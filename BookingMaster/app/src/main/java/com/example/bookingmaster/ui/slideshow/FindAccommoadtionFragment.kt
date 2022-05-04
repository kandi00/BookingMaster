package com.example.bookingmaster.ui.slideshow
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookingmaster.R
import com.example.bookingmaster.databinding.FragmentAccommodationBinding
import com.example.bookingmaster.databinding.FragmentFindAccommoadtionBinding
import com.example.bookingmaster.model.BookingRequest
import com.example.bookingmaster.viewmodel.ListViewModel
import com.google.android.material.datepicker.*
import com.google.android.material.snackbar.Snackbar
import java.util.*


class FindAccommodation : Fragment() {

    private var _binding: FragmentFindAccommoadtionBinding? = null
    private val binding get() = _binding!!
    private lateinit var fragment: View
    private lateinit var listViewModel: ListViewModel
    private lateinit var fromDateText: TextView
    private lateinit var toDateText: TextView
    private lateinit var fromDateButton: Button
    private lateinit var toDateButton: Button
    private lateinit var goButton: Button
    private lateinit var fromDate: String
    private lateinit var toDate: String
    private lateinit var accommodationNameEditText: EditText
    private var fromDateSelection: Long? = null
    private var toDateSelection: Long? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listViewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFindAccommoadtionBinding.inflate(inflater, container, false)
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
        goButton = binding.goButton
        fromDate = Calendar.getInstance().time.toString()
        toDate = Calendar.getInstance().time.toString()
        fromDateSelection = Calendar.getInstance().timeInMillis - 1
        toDateSelection = Calendar.getInstance().timeInMillis + 100000000000
        accommodationNameEditText = binding.etFindAccommodationByName
    }

    private fun openDatePicker(textView: TextView, text: String) {
        val constraintsBuilderRange = CalendarConstraints.Builder()

        if (text == "Check-out date: ") {
            constraintsBuilderRange.setValidator(DateValidatorPointForward.from(fromDateSelection!!))
        } else {
            val dateValidatorMin: CalendarConstraints.DateValidator = DateValidatorPointForward.now()
            val dateValidatorMax: CalendarConstraints.DateValidator =
                DateValidatorPointBackward.before(toDateSelection!!)
            val listValidators = ArrayList<CalendarConstraints.DateValidator>()
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

        goButton.setOnClickListener {

            try {
                listViewModel.getAccommodations(accommodationNameEditText.text.toString(), fromDate, toDate)
                findNavController().navigate(R.id.action_nav_search_for_accommodation_to_nav_accommodation)
            } catch (e: Exception) {
                Log.d("BookFragment", "Exception while adding new booking: $e")
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}