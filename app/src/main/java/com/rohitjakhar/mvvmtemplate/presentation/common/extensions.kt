package com.rohitjakhar.mvvmtemplate.presentation.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rohitjakhar.mvvmtemplate.MyApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

inline fun <T> basicDiffUtil(
    crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
    crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
) = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        areItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        areContentsTheSame(oldItem, newItem)
}

fun <T> LifecycleOwner.launchAndCollect(
    flow: Flow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    body: (T) -> Unit
) {
    lifecycleScope.launch {
        this@launchAndCollect.repeatOnLifecycle(state) {
            flow.collect(body)
        }
    }
}

val Context.app: MyApp get() = applicationContext as MyApp

val Fragment.app: MyApp get() = requireContext().app


//Glide.with(context)
//.load(item.profileImageUrl)
//.listener(object : RequestListener<Drawable> {
//    override fun onLoadFailed(
//        e: GlideException?,
//        model: Any?,
//        target: Target<Drawable>?,
//        isFirstResource: Boolean
//    ): Boolean {
//        e?.logRootCauses("GlideError")
//        Log.e("GlideError", "onLoadFailed: ${e?.message}")
//        return false
//    }
//
//    override fun onResourceReady(
//        resource: Drawable?,
//        model: Any?,
//        target: com.bumptech.glide.request.target.Target<Drawable>?,
//        dataSource: com.bumptech.glide.load.DataSource?,
//        isFirstResource: Boolean,
//    ): Boolean {
//        return false
//    }
//
//})
//.into(binding.imvPhotoPlace)