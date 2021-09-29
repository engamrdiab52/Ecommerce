package com.amrabdelhamiddiab.bags.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.amrabdelhamiddiab.bags.R
import com.amrabdelhamiddiab.bags.databinding.FragmentFavoritesDetailsBinding
import com.amrabdelhamiddiab.bags.framework.LoginFlowViewModelFactory
import com.amrabdelhamiddiab.core.domain.Bag
import com.google.firebase.auth.FirebaseAuth

class FavoritesDetailsFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesDetailsBinding
    private var favoriteBag: Bag? = null
    private val viewModel: FavoriteViewModel by navGraphViewModels(R.id.nested_graph_favorites) {
        LoginFlowViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorites_details, container, false)
        viewModel.favoriteBag.observe(viewLifecycleOwner, {
            binding.bag = it
            favoriteBag = it
        })
        binding.btnDetailsFavoritesDelete.setOnClickListener {
            val userId: String? = FirebaseAuth.getInstance().currentUser?.uid
            favoriteBag?.let { it1 ->
                if (userId != null) {
                    viewModel.removeItem(userId, it1)
                }
            }
        }
        binding.btnDetailsFavoritesAddToCart.setOnClickListener {
            val userId: String? = FirebaseAuth.getInstance().currentUser?.uid
            favoriteBag?.let { it1 ->
                if (userId != null) {
                    viewModel.addItemToCartList(userId, it1)
                }
            }
        }
        viewModel.uploadTask.observe(viewLifecycleOwner, {
            if (it == true) {
               findNavController().popBackStack()
            } else {
                Toast.makeText(
                    requireContext(),
                    "something wrong happened check internet connection",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        return binding.root
    }
}