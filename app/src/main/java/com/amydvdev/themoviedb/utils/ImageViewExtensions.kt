package com.amydvdev.themoviedb.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Loads image from the url.
 * @param imgUrl string url
 */
fun ImageView.loadImage(imgUrl: String? = null) {
    Glide.with(this)
        .load(imgUrl)
        .into(this)
}

/**
 * Loads image from the url.
 * @param imgUrl string url
 */
fun ImageView.loadCircularImage(imgUrl: String? = null, @DrawableRes defaultImg: Int = 0) {
    val loadImg = imgUrl ?: defaultImg
    Glide.with(this)
        .load(loadImg)
        .circleCrop()
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .into(this)
}