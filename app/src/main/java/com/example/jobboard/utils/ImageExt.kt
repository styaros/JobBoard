package com.example.jobboard.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.src(uri: String?) {
    Glide.with(context)
        .load(uri)
        .into(this)
}

fun ImageView.src(@DrawableRes res: Int?) {
    Glide.with(context)
        .load(res)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}