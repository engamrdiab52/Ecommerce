package com.example.bags.presentation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.bags.framework.glide.GlideApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        //Note glide downloads images in background thread
        GlideApp.with(view.context).load(Firebase.storage.getReferenceFromUrl(url)).into(view)
    }

}