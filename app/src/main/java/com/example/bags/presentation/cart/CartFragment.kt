package com.example.bags.presentation.cart

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.bags.MainActivity
import com.example.bags.R
import com.example.bags.databinding.FragmentCartBinding
import com.example.bags.framework.LoginFlowViewModelFactory
import com.example.bags.presentation.favorite.FavoriteListEpoxyController
import com.example.bags.presentation.favorite.FavoriteViewModel
import com.example.core.domain.Bag

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
    private lateinit var layoutManagerLinear: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        recyclerView = binding.recyclerViewCart
        recyclerView.adapter = cartListEpoxyController.adapter
        layoutManagerLinear = LinearLayoutManager(context)
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
            if (it.isNullOrEmpty()){
                recyclerView.layoutManager = layoutManagerLinear
                cartListEpoxyController.setData(it)
                Log.d(MainActivity.TAG, it.toString())
            }else{
                recyclerView.layoutManager = layoutManager
                cartListEpoxyController.setData(it)
                Log.d(MainActivity.TAG, it.toString())
            }
        })
        viewModel.cardClicked.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_cartFragment_to_cartDetailsFragment)
        })
        viewModel.downloadCartItems()
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        var tempList: List<Bag> = listOf()
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.search(newText, tempList)
                return true
            }
        })
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                tempList = viewModel.listOfCartItems.value!!
                return true
            }
            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }


}