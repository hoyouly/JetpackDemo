package com.hoyouly.jetpackdemo.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * @ Time  :  2020-08-27
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}


@BindingAdapter("imageTransFromUrl")
fun bindImageTransFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .apply(
                RequestOptions.bitmapTransform(
                    RoundedCornersTransformation(
                        20,
                        0,
                        RoundedCornersTransformation.CornerType.ALL
                    )
                )
            )
            .into(view)
    }
}

