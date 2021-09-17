package com.example.bags.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.bags.R
import com.example.bags.databinding.FragmentHomeBinding
import com.example.bags.framework.PreferenceManager
import com.example.core.data.IPreferenceHelper

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var preferenceHelper: IPreferenceHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
        }
        binding.btnToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_global_nested_graph_login)
        }
        preferenceHelper = PreferenceManager(requireActivity().applicationContext)
      /*  preferenceHelper.setUserLoggedIn(false)
        if ( preferenceHelper.getUserLoggedIn()){
            findNavController().navigate(R.id.action_global_nested_graph_login)
        }*/
        // Inflate the layout for this fragment
        return binding.root
    }

}