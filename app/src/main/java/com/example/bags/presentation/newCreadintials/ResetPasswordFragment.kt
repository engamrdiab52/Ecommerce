package com.example.bags.presentation.newCreadintials

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
import com.example.bags.R
import com.example.bags.databinding.FragmentResetPasswordBinding
import com.example.bags.framework.LoginFlowViewModelFactory
import com.example.bags.presentation.LoginFlowViewModel
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator

class ResetPasswordFragment : Fragment() {
    private val viewModel: LoginFlowViewModel by navGraphViewModels(R.id.nested_graph_login){
        LoginFlowViewModelFactory
    }
    private lateinit var binding: FragmentResetPasswordBinding
    private var validEmail: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_reset_password, container, false)
        binding.btnSendVerificationEmail.setOnClickListener {
            validateEmailField()
            if (validEmail ){
                val email = binding.editTextEmailResetPassword.text.toString()
                viewModel.resetPassword(email)
                findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
            }else{
                Toast.makeText(requireContext(), "** INVALID CREDENTIALS **", Toast.LENGTH_LONG)
                    .show()
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun validateEmailField() {
        binding.editTextEmailResetPassword.validator().nonEmpty().validEmail().addErrorCallback {
            validEmail = false
            binding.textLayoutEmailResetPassword.error = it
            Log.d(MainActivity.TAG, it)
        }.addSuccessCallback {
            validEmail = true
            binding.textLayoutEmailResetPassword.error = null
        }.check()
    }

}