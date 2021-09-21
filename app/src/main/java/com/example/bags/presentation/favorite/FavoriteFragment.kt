package com.example.bags.presentation.favorite

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
import com.example.bags.MainActivity
import com.example.bags.R
import com.example.bags.databinding.FragmentFavoriteBinding
import com.example.bags.databinding.FragmentHomeBinding
import com.example.bags.framework.LoginFlowViewModelFactory
import com.example.bags.framework.PreferenceManager
import com.example.core.data.IPreferenceHelper

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    private lateinit var layoutManager: GridLayoutManager
    private val favoriteListEpoxyController by lazy {
        FavoriteListEpoxyController()
    }
    private lateinit var recyclerView: EpoxyRecyclerView
    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this, LoginFlowViewModelFactory)[FavoriteViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        recyclerView = binding.recyclerView
        recyclerView.adapter = favoriteListEpoxyController.adapter
        layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager
        viewModel.downloading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.loadingIndecatorHome.visibility = View.VISIBLE
            } else {
                binding.loadingIndecatorHome.visibility = View.GONE
            }
        })
        viewModel.listOfFavorites.observe(viewLifecycleOwner, {
            favoriteListEpoxyController.setData(it)
            Log.d(MainActivity.TAG, it.toString())
        })

        viewModel.downloadFavorites()
        // Inflate the layout for this fragment
        return binding.root
    }

}