package com.example.bags.presentation.favorite

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.bags.MainActivity
import com.example.bags.R
import com.example.bags.databinding.FragmentFavoriteBinding
import com.example.bags.databinding.FragmentHomeBinding
import com.example.bags.framework.LoginFlowViewModelFactory
import com.example.bags.framework.PreferenceManager
import com.example.core.data.IPreferenceHelper
import com.example.core.domain.Bag

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    private lateinit var layoutManager: GridLayoutManager
    private lateinit var layoutManagerLinear: LinearLayoutManager
    private val favoriteListEpoxyController by lazy {
        FavoriteListEpoxyController(viewModel)
    }
    private lateinit var recyclerView: EpoxyRecyclerView
    private val viewModel: FavoriteViewModel by navGraphViewModels(R.id.nested_graph_favorites) {
        LoginFlowViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        recyclerView = binding.recyclerView
        recyclerView.adapter = favoriteListEpoxyController.adapter
        layoutManagerLinear = LinearLayoutManager(context)
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
            if (it.isNullOrEmpty()){
                recyclerView.layoutManager = layoutManagerLinear
                favoriteListEpoxyController.setData(it)
                Log.d(MainActivity.TAG, it.toString())
            }else{
                recyclerView.layoutManager = layoutManager
                favoriteListEpoxyController.setData(it)
                Log.d(MainActivity.TAG, it.toString())
            }
        })
        viewModel.cardClicked.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_favoriteFragment_to_favoritesDetailsFragment2)
        })
        viewModel.downloadFavorites()
        // Inflate the layout for this fragment
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
                tempList = viewModel.listOfFavorites.value!!
                return true
            }
            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

}