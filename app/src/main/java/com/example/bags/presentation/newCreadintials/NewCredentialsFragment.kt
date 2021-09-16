package com.example.bags.presentation.newCreadintials

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.bags.MainActivity
import com.example.bags.MainActivity.Companion.TAG
import com.example.bags.R
import com.example.bags.databinding.FragmentNewCredentialsBinding
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator

class NewCredentialsFragment : Fragment() {
    private lateinit var binding: FragmentNewCredentialsBinding
    private var validPassword: Boolean = false
    private var validConfirmPassword: Boolean = false
    private var errorMessage: String? = "Can't be empty!"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_new_credentials, container, false)

        binding.editTxtCredentialsPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                s.toString().validator().nonEmpty().atleastOneNumber().atleastOneSpecialCharacters()
                    .minLength(6).addErrorCallback {
                        errorMessage = it
                        binding.textLayoutCredentialsPassword.helperText = it
                        validPassword = false
                        Log.d(TAG, it)
                    }.addSuccessCallback {
                        binding.textLayoutCredentialsPassword.helperText = null
                        validPassword = true
                        errorMessage = null
                        Log.d(TAG, "SUCCESS")
                    }.check()
            }

        })

        binding.btnNewCredentialsUpdate.setOnClickListener {
            binding.textLayoutCredentialsPassword.error = errorMessage
            validateConfirmPasswordField()

            if (validConfirmPassword && validPassword) {
                findNavController().navigate(R.id.action_newCredentialsFragment_to_loginFragment)
            } else {
                Toast.makeText(requireContext(), "** INVALID CREDENTIALS **", Toast.LENGTH_LONG)
                    .show()
            }


            //findNavController().navigate(R.id.action_newCredentialsFragment_to_loginFragment)
        }
        binding.btnToHome.setOnClickListener {
            findNavController().navigate(R.id.action_global_nested_graph_home)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun validateConfirmPasswordField() {
        binding.editTxtCredentialsConfirmPassword.text.toString().validator().nonEmpty()
            .addErrorCallback {
                validConfirmPassword = false
                binding.textLayoutCredentialsConfirmPassword.error = it
                binding.textLayoutCredentialsPassword.error = errorMessage
            }.addSuccessCallback {
                val str1 = binding.editTxtCredentialsConfirmPassword.text.toString()
                val str2 = binding.editTxtCredentialsPassword.text.toString()
                val matched = str1 == str2
                if (!matched) {
                    binding.textLayoutCredentialsPassword.error = errorMessage
                    binding.textLayoutCredentialsConfirmPassword.error =
                        "Passwords do not match"
                    validConfirmPassword = false
                } else {
                    if (!validPassword || str1.isEmpty()) {
                        binding.textLayoutCredentialsPassword.error = errorMessage
                        binding.textLayoutCredentialsConfirmPassword.error =
                            "Passwords are matched but not correct password"
                        validConfirmPassword = false
                    } else {
                        binding.textLayoutCredentialsConfirmPassword.helperText =
                            "Passwords are matched"
                        validConfirmPassword = true
                    }

                }
            }.check()
    }
}