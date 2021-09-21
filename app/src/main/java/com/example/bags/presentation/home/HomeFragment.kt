package com.example.bags.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.bags.MainActivity.Companion.TAG
import com.example.bags.R
import com.example.bags.databinding.FragmentHomeBinding
import com.example.bags.framework.LoginFlowViewModelFactory
import com.example.bags.framework.PreferenceManager
import com.example.bags.presentation.favorite.FavoriteListEpoxyController
import com.example.bags.presentation.favorite.FavoriteViewModel
import com.example.core.data.IPreferenceHelper

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var preferenceHelper: IPreferenceHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        preferenceHelper = PreferenceManager(requireActivity().applicationContext)
        if (!preferenceHelper.getUserLoggedIn()) {
            findNavController().navigate(R.id.action_global_nested_graph_login)
        }
        preferenceHelper = PreferenceManager(requireActivity().applicationContext)
        return binding.root
    }

}