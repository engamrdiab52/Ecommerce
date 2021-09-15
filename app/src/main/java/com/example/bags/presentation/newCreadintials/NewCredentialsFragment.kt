package com.example.bags.presentation.newCreadintials

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.bags.R
import com.example.bags.databinding.FragmentNewCredentialsBinding

class NewCredentialsFragment : Fragment() {
    private lateinit var binding: FragmentNewCredentialsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_new_credentials, container, false)
        binding.btnNewCredentialsUpdate.setOnClickListener {
            findNavController().navigate(R.id.action_newCredentialsFragment_to_loginFragment)
        }
        binding.btnToHome.setOnClickListener {
            findNavController().navigate(R.id.action_global_nested_graph_home)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

}