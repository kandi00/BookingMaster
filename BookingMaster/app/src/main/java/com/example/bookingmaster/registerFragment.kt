package com.example.bookingmaster

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bookingmaster.api.BookingMasterRepository
import com.example.bookingmaster.model.RegistrationRequest
import kotlinx.coroutines.launch

class registerFragment : Fragment() {

    private lateinit var registerButton: Button
    private lateinit var backButton: Button
    private lateinit var displayNameET: EditText
    private lateinit var userNameET: EditText
    private lateinit var emailET: EditText
    private lateinit var passwordET: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        view?.apply {

            initializeView(view)
        }
        return view
    }

    private fun initializeView(view: View) {
        registerButton = view.findViewById(R.id.register_button_regfragment)

        backButton = view.findViewById(R.id.back_button_regfragment)
        displayNameET = view.findViewById(R.id.displayname_ET_regfragment)
        userNameET = view.findViewById(R.id.username_ET_regfragment)
        emailET = view.findViewById(R.id.email_ET_regfragment)
        passwordET = view.findViewById(R.id.password_ET_regfragment)

        registerButton.setOnClickListener {
            var isValid = true
            if (userNameET.text.toString().trim().isEmpty()){
                isValid = false
                userNameET.setError("Username required")
            }
            if (passwordET.text.toString().trim().isEmpty()){
                isValid = false
                passwordET.setError("Password required")
            }
            if (displayNameET.text.toString().trim().isEmpty()){
                isValid = false
                displayNameET.setError("display name required")
            }
            if (emailET.text.toString().trim().isEmpty()){
                isValid = false
                emailET.setError("email required")
            }

            //only start the login process if the fields are not empty
            if (isValid) {

                lifecycleScope.launch() {
                    register()
                }
                //Toast.makeText(requireContext(), "Register probably Successfull (backend doesnt return code)", Toast.LENGTH_LONG).show()
                //findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }

    }

    suspend fun register() {
        val repository = BookingMasterRepository()
        val request = RegistrationRequest(displayName = displayNameET.text.toString(), userName = userNameET.text.toString(), email = emailET.text.toString(), password = passwordET.text.toString())
        try {
            //to the register request the backend returns an HTML file not the specified JSON with result_code so no implementation for now
            val result = repository.register(request)
            Toast.makeText(requireContext(), "Register probably Successfull (backend doesnt return code)", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

        } catch (e: Exception) {
            Log.d("xxx", "RegisterFragment - exception: ${e.toString()}")
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}