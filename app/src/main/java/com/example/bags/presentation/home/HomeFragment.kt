package com.example.bags.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.bags.MainActivity
import com.example.bags.MainActivity.Companion.TAG
import com.example.bags.R
import com.example.bags.databinding.FragmentHomeBinding
import com.example.bags.framework.LoginFlowViewModelFactory
import com.example.bags.framework.PreferenceManager
import com.example.core.data.IPreferenceHelper

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var preferenceHelper: IPreferenceHelper
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var recyclerView: EpoxyRecyclerView

    private val categoriesEpoxyController by lazy {
        CategoriesEpoxyController(viewModel)
    }
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, LoginFlowViewModelFactory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        preferenceHelper = PreferenceManager(requireActivity().applicationContext)
        if (!preferenceHelper.getUserLoggedIn()) {
            findNavController().navigate(R.id.action_global_nested_graph_login)
        }
        recyclerView = binding.recyclerViewHome
        recyclerView.adapter = categoriesEpoxyController.adapter
        layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager
        viewModel.downloading.observe(viewLifecycleOwner, {
            if (it) {
                binding.loadingIndecatorHome.visibility = View.VISIBLE
            } else {
                binding.loadingIndecatorHome.visibility = View.GONE
            }
        })

        viewModel.listOfCategories.observe(viewLifecycleOwner, {
            categoriesEpoxyController.setData(it)
            Log.d(TAG, it.toString())
        })
        viewModel.womenCategoryClicked.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "CARD clicked.........")
            findNavController().navigate(R.id.action_homeFragment_to_nested_graph_women_bags)
        })
        viewModel.cardClicked.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "NOT implemented yet", Toast.LENGTH_SHORT).show()
        })
        viewModel.downloadCategories()
        return binding.root
    }

}
//findNavController().navigate(R.id.action_homeFragment_to_categoryWomenFragment)