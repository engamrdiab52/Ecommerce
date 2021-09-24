package com.example.bags.presentation.categories.women

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
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.bags.MainActivity
import com.example.bags.R
import com.example.bags.databinding.FragmentCategoryWomenBinding
import com.example.bags.framework.LoginFlowViewModelFactory

class CategoryWomenFragment : Fragment() {
    private lateinit var binding: FragmentCategoryWomenBinding
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var recyclerView: EpoxyRecyclerView
    private val viewModel: CategoryWomenViewModel by navGraphViewModels(R.id.nested_graph_women_bags) {
        LoginFlowViewModelFactory
    }
    private val categoryWomenEpoxyController by lazy {
        CategoryWomenEpoxyController(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_category_women, container, false)
        recyclerView = binding.recyclerViewCategoryWomen
        recyclerView.adapter = categoryWomenEpoxyController.adapter
        layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager
        viewModel.downloading.observe(viewLifecycleOwner,  {
            if (it) {
                binding.loadingIndecatorCategoryWomen.visibility = View.VISIBLE
            } else {
                binding.loadingIndecatorCategoryWomen.visibility = View.GONE
            }
        })
        viewModel.listOfCategoryWomen.observe(viewLifecycleOwner, {
            categoryWomenEpoxyController.setData(it)
            Log.d(MainActivity.TAG, it.toString())
        })
        viewModel.cardClicked.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_categoryWomenFragment_to_detailsFragment)
            })
        viewModel.downloadCategoryWomen()
        // Inflate the layout for this fragment
        return binding.root
    }


}