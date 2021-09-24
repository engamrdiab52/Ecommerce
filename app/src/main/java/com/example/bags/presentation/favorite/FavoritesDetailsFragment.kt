package com.example.bags.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.bags.R
import com.example.bags.databinding.FragmentFavoritesDetailsBinding
import com.example.bags.framework.LoginFlowViewModelFactory
import com.example.bags.presentation.categories.women.CategoryWomenViewModel
import com.example.core.domain.Bag
import com.example.core.domain.FavoriteOrder
import com.google.firebase.auth.FirebaseAuth

class FavoritesDetailsFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesDetailsBinding
    private var favoriteBag: FavoriteOrder? = null
    private val viewModel: FavoriteViewModel by navGraphViewModels(R.id.nested_graph_favorites) {
        LoginFlowViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorites_details, container, false)
        viewModel.favoriteBag.observe(viewLifecycleOwner, {
            binding.favoriteBag = it
            favoriteBag = it
        })
        binding.btnDetailsFavoritesDelete.setOnClickListener {
            val userId: String? = FirebaseAuth.getInstance().currentUser?.uid
            favoriteBag?.let { it1 ->
                val bag = Bag(
                    it1.id_product,
                    it1.name_product,
                    it1.url_product,
                    it1.price_product,
                    it1.description_product
                )
                if (userId != null) {
                    viewModel.removeItem(userId, bag)
                }
            }
        }
        viewModel.uploadTask.observe(viewLifecycleOwner, {
            if (it) {
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