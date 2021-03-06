package com.amrabdelhamiddiab.bags.framework.glide

import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.google.firebase.storage.StorageReference
import java.io.InputStream

@GlideModule
class BagsAppGlideModule : AppGlideModule() {
    override fun registerComponents(
        context: android.content.Context,
        glide: Glide,
        registry: Registry
    ) {
        registry.append(
            StorageReference::class.java,
            InputStream::class.java,
            FirebaseImageLoader.Factory()
        )
    }
}