package com.example.bags.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.bags.R
import com.example.bags.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.btnDetailsAddToFavorite.setOnClickListener {
            Toast.makeText(requireContext(), "adding it to favorite list", Toast.LENGTH_SHORT)
                .show()
        }
        // Inflate the layout for this fragment
        return binding.root
    }

}