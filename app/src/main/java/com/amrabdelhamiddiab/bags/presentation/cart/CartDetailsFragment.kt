package com.amrabdelhamiddiab.bags.presentation.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.amrabdelhamiddiab.bags.R
import com.amrabdelhamiddiab.bags.databinding.FragmentCartDetailsFragmrntBinding
import com.amrabdelhamiddiab.bags.framework.LoginFlowViewModelFactory
import com.amrabdelhamiddiab.core.domain.Bag
import com.google.firebase.auth.FirebaseAuth

class CartDetailsFragment : Fragment() {
    private lateinit var binding: FragmentCartDetailsFragmrntBinding
    private var favoriteBag: Bag? = null
    private val viewModel: CartViewModel by navGraphViewModels(R.id.nested_graph_cart) {
        LoginFlowViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_cart_details_fragmrnt,
            container,
            false
        )
        viewModel.cartBag.observe(viewLifecycleOwner, {
            binding.bag = it
            favoriteBag = it
        })
        binding.btnDetailsCartDelete.setOnClickListener {
            val userId: String? = FirebaseAuth.getInstance().currentUser?.uid
            favoriteBag?.let { it1 ->
                if (userId != null) {
                    viewModel.removeItem(userId, it1)
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
        // Inflate the layout for this fragment
        return binding.root
    }

}