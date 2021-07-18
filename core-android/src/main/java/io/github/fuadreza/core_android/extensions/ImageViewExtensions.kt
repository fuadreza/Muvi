package io.github.fuadreza.core_android.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadBackdrop(url: String){
    Glide.with(this.context)
        .load("https://image.tmdb.org/t/p/w500$url")
        .into(this)
}