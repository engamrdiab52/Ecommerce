package com.example.bags.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.bags.R
import com.example.bags.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.buttonLogin.setOnClickListener {
          //  findNavController().navigate(R.id.actio)
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

}