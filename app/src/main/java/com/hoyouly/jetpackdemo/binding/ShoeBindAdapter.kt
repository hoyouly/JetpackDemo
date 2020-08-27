package com.hoyouly.jetpackdemo.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * @ Time  :  2020-08-27
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String) {
    if (imageUrl.isNotEmpty()) {
        Glide.with(view.context).load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade()).into(view)
    }
}