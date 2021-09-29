package com.amrabdelhamiddiab.bags.presentation.categories.women

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.navGraphViewModels
import com.amrabdelhamiddiab.bags.R
import com.amrabdelhamiddiab.bags.databinding.FragmentWomenDetailsBinding
import com.amrabdelhamiddiab.bags.framework.LoginFlowViewModelFactory
import com.amrabdelhamiddiab.core.domain.Bag
import com.google.firebase.auth.FirebaseAuth

class WomenDetailsFragment : Fragment() {
    private lateinit var binding: FragmentWomenDetailsBinding
    private val viewModel: CategoryWomenViewModel by navGraphViewModels(R.id.nested_graph_women_bags) {
        LoginFlowViewModelFactory
    }
    private var bag: Bag? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_women_details, container, false)

        viewModel.bag.observe(viewLifecycleOwner, {
            binding.bag = it
            bag = it
        })
        binding.btnDetailsWomenAddToFavorite.setOnClickListener {
            val userId: String? = FirebaseAuth.getInstance().currentUser?.uid
            bag?.let { it1 ->
                if (userId != null) {
                    viewModel.uploadFavoriteItem(userId, it1)
                }
            }

        }
        viewModel.uploadTask.observe(viewLifecycleOwner, {
            if (it == true) {
                Toast.makeText(requireContext(), "added to favorite", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "something wrong happened check internet connection",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
        binding.btnDetailsWomenAddToCart.setOnClickListener {
            val userId: String? = FirebaseAuth.getInstance().currentUser?.uid
            bag?.let { it1 ->
                if (userId != null) {
                    viewModel.addItemToCartList(userId, it1)
                }
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

}