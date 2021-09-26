package com.example.bags.presentation.categories.women

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
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
import com.example.bags.databinding.FragmentCategoryWomenBinding
import com.example.bags.framework.LoginFlowViewModelFactory
import com.example.core.domain.Bag

class CategoryWomenFragment : Fragment() {
    private lateinit var binding: FragmentCategoryWomenBinding
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var layoutManagerLinear: LinearLayoutManager
    private lateinit var recyclerView: EpoxyRecyclerView
    private val viewModel: CategoryWomenViewModel by navGraphViewModels(R.id.nested_graph_women_bags) {
        LoginFlowViewModelFactory
    }
    private val categoryWomenEpoxyController by lazy {
        CategoryWomenEpoxyController(viewModel)
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
                tempList = viewModel.listOfCategoryWomen.value!!
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_category_women, container, false)
        recyclerView = binding.recyclerViewCategoryWomen
        recyclerView.adapter = categoryWomenEpoxyController.adapter
        layoutManagerLinear = LinearLayoutManager(context)
        layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager
        viewModel.downloading.observe(viewLifecycleOwner, {
            if (it) {
                binding.loadingIndecatorCategoryWomen.visibility = View.VISIBLE
            } else {
                binding.loadingIndecatorCategoryWomen.visibility = View.GONE
            }
        })
        viewModel.listOfCategoryWomen.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()){
                recyclerView.layoutManager = layoutManagerLinear
                categoryWomenEpoxyController.setData(it)
                Log.d(MainActivity.TAG, it.toString())
            }else{
                recyclerView.layoutManager = layoutManager
                categoryWomenEpoxyController.setData(it)
                Log.d(MainActivity.TAG, it.toString())
            }

        })
        viewModel.cardClicked.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_categoryWomenFragment_to_detailsFragment)
        })
        viewModel.downloadCategoryWomen()
        // Inflate the layout for this fragment
        return binding.root
    }


}