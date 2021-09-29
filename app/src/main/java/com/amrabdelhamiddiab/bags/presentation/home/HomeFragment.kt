package com.amrabdelhamiddiab.bags.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.amrabdelhamiddiab.bags.MainActivity.Companion.TAG
import com.amrabdelhamiddiab.bags.R
import com.amrabdelhamiddiab.bags.databinding.FragmentHomeBinding
import com.amrabdelhamiddiab.bags.framework.LoginFlowViewModelFactory
import com.amrabdelhamiddiab.bags.framework.PreferenceManager
import com.amrabdelhamiddiab.core.data.IPreferenceHelper

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
    ): View {
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
        viewModel.womenCategoryClicked.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_homeFragment_to_nested_graph_women_bags)
        })
        viewModel.cardClicked.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "NOT implemented yet", Toast.LENGTH_SHORT).show()
        })
        viewModel.downloadCategories()
        return binding.root
    }

}
//findNavController().navigate(R.id.action_homeFragment_to_categoryWomenFragment)