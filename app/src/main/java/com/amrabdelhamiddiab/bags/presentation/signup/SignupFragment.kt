package com.amrabdelhamiddiab.bags.presentation.signup

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
import androidx.navigation.navGraphViewModels
import com.amrabdelhamiddiab.bags.MainActivity
import com.amrabdelhamiddiab.bags.MainActivity.Companion.TAG
import com.amrabdelhamiddiab.bags.R
import com.amrabdelhamiddiab.bags.databinding.FragmentSignupBinding
import com.amrabdelhamiddiab.bags.framework.LoginFlowViewModelFactory
import com.amrabdelhamiddiab.bags.framework.utilis.checkInternetConnection
import com.amrabdelhamiddiab.bags.presentation.LoginFlowViewModel
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator

class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private val viewModel: LoginFlowViewModel by navGraphViewModels(R.id.nested_graph_login) {
        LoginFlowViewModelFactory
    }
    private var validPassword: Boolean = false
    private var validEmail: Boolean = false
    private var validConfirmPassword: Boolean = false
    private var validUserName: Boolean = false
    var errorMessage: String? = "Can't be empty!"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)

        viewModel.downloading.observe(viewLifecycleOwner, {
            if (it) {
                binding.loadingIndecator.visibility = View.VISIBLE
            } else {
                binding.loadingIndecator.visibility = View.GONE
            }
        })

        viewModel.emailVerificationSent.observe(viewLifecycleOwner, {
            if (it == true) {
                Toast.makeText(requireContext(), "Email sent successfully", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
            } else {
                Toast.makeText(requireContext(), "Sending was not completed", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        viewModel.userCreated.observe(viewLifecycleOwner, {
            if (it == true) {
                viewModel.sendVerificationEmail()
                Log.d(TAG, "trying sending an email")
            } else {
                Log.d(TAG, "The account has not been created")
                Toast.makeText(
                    requireContext(),
                    "The account has not been created",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

        binding.txtViewSignupSignin.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        binding.edtTxtSignupPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                s.toString().validator().nonEmpty().atleastOneNumber().atleastOneSpecialCharacters()
                    .minLength(6).addErrorCallback {
                        errorMessage = it
                        binding.txtLayoutSignupPassword.helperText = it
                        validPassword = false
                        Log.d(TAG, it)
                    }.addSuccessCallback {
                        binding.txtLayoutSignupPassword.helperText = null
                        validPassword = true
                        errorMessage = null
                        Log.d(TAG, "SUCCESS")
                    }.check()
            }
        })



        binding.btnSignupSignup.setOnClickListener {
            binding.txtLayoutSignupPassword.error = errorMessage
            validateNameField()
            validateEmailField()
            validateConfirmPasswordField()
            if (validEmail && validUserName && validPassword && validConfirmPassword) {
                val password = binding.edtTxtSignupPassword.text.toString()
                val email = binding.edtTxtSignupEmail.text.toString()
                if (checkInternetConnection(requireContext())) {
                    viewModel.createUser(email, password)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "No Network please turn on",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                //may use singleLiveEvent
                Toast.makeText(requireContext(), "** INVALID CREDENTIALS **", Toast.LENGTH_LONG)
                    .show()
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun validateNameField() {
        binding.edtTxtSignupName.text.toString().validator().nonEmpty().addErrorCallback {
            binding.txtLayoutSignupName.error = it
            validUserName = false
        }.addSuccessCallback {
            binding.txtLayoutSignupName.error = null
            validUserName = true
        }.check()
    }

    private fun validateEmailField() {
        binding.edtTxtSignupEmail.text.toString().validator().nonEmpty().validEmail()
            .addErrorCallback {
                binding.txtLayoutSignupEmail.error = it
                validEmail = false
            }.addSuccessCallback {
                binding.txtLayoutSignupEmail.error = null
                validEmail = true
            }.check()
    }

    private fun validateConfirmPasswordField() {
        binding.edtTxtSignupConfirmPassword.text.toString().validator().nonEmpty()
            .addErrorCallback {
                validConfirmPassword = false
                binding.txtLayoutSignupConfirmPassword.error = it
                binding.txtLayoutSignupPassword.error = errorMessage
            }.addSuccessCallback {
                val str1 = binding.edtTxtSignupConfirmPassword.text.toString()
                val str2 = binding.edtTxtSignupPassword.text.toString()
                val matched = str1 == str2
                if (!matched) {
                    binding.txtLayoutSignupPassword.error = errorMessage
                    binding.txtLayoutSignupConfirmPassword.error = "Passwords do not match"
                    validConfirmPassword = false
                } else {
                    if (!validPassword || str1.isEmpty()) {
                        binding.txtLayoutSignupPassword.error = errorMessage
                        binding.txtLayoutSignupConfirmPassword.error =
                            "Passwords are matched but not correct password"
                        validConfirmPassword = false
                    } else {
                        binding.txtLayoutSignupConfirmPassword.helperText = "Passwords are matched"
                        validConfirmPassword = true
                    }
                }
            }.check()
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setDrawerLocked()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).setDrawerUnlocked()
    }
}