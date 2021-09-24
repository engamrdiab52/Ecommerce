package com.example.bags.presentation.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.bags.MainActivity
import com.example.bags.R
import com.example.bags.databinding.FragmentCartBinding
import com.example.bags.framework.LoginFlowViewModelFactory
import com.example.bags.presentation.favorite.FavoriteListEpoxyController
import com.example.bags.presentation.favorite.FavoriteViewModel

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private val viewModel: CartViewModel by navGraphViewModels(R.id.nested_graph_cart) {
        LoginFlowViewModelFactory
    }
    private lateinit var layoutManager: GridLayoutManager
    private val cartListEpoxyController by lazy {
        CartListEpoxyController(viewModel)
    }
    private lateinit var recyclerView: EpoxyRecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        recyclerView = binding.recyclerViewCart
        recyclerView.adapter = cartListEpoxyController.adapter
        layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager
        viewModel.downloading.observe(viewLifecycleOwner, {
            if (it) {
                binding.loadingIndecatorCart.visibility = View.VISIBLE
            } else {
                binding.loadingIndecatorCart.visibility = View.GONE
            }
        })
        viewModel.listOfCartItems.observe(viewLifecycleOwner, {
            cartListEpoxyController.setData(it)
            Log.d(MainActivity.TAG, it.toString())
        })
        viewModel.cardClicked.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_cartFragment_to_cartDetailsFragment)
        })
        viewModel.downloadCartItems()
        return binding.root
    }

}