package com.example.bags.presentation.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.bags.MainActivity
import com.example.bags.MainActivity.Companion.TAG
import com.example.bags.R
import com.example.bags.databinding.FragmentLoginBinding
import com.example.bags.framework.LoginFlowViewModelFactory
import com.example.bags.presentation.LoginFlowViewModel
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator

class LoginFragment : Fragment() {
    private val viewModel: LoginFlowViewModel by navGraphViewModels(R.id.nested_graph_login) {
        LoginFlowViewModelFactory
    }
    private lateinit var binding: FragmentLoginBinding
    private var validPassword: Boolean = false
    private var validEmail: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.buttonLogin.setOnClickListener {
            validateEmailField()
            validatePasswordField()
            if (validEmail && validPassword) {
                val email = binding.editTextLoginEmail.text.toString()
                val password = binding.editTextLoginPassword.text.toString()
                viewModel.signIn(email, password)
                Toast.makeText(requireContext(), "$email + $password", Toast.LENGTH_SHORT).show()
            } else {
                //may use singleLiveEvent
                Toast.makeText(requireContext(), "** INVALID CREDENTIALS **", Toast.LENGTH_LONG)
                    .show()
            }
        }
        binding.tvLoginForgetPasswordClickable.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_newCredentialsFragment)
        }
        binding.tvLoginSignupClickable.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun validatePasswordField() {
        binding.editTextLoginPassword.validator().nonEmpty().addErrorCallback {
            validPassword = false
            binding.textLayoutLoginPassword.error = it
            Log.d(TAG, it)
        }.addSuccessCallback {
            validPassword = true
            binding.textLayoutLoginPassword.error = null
        }.check()
    }

    private fun validateEmailField() {
        binding.editTextLoginEmail.validator().nonEmpty().validEmail().addErrorCallback {
            validEmail = false
            binding.textLayoutLoginEmail.error = it
            Log.d(TAG, it)
        }.addSuccessCallback {
            validEmail = true
            binding.textLayoutLoginEmail.error = null
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