package com.qifan.theforktest.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.qifan.theforktest.R
import com.squareup.picasso.Picasso

fun ImageView.load(url: String, @DrawableRes placeholder: Int = R.drawable.ic_launcher_background) {
    Picasso.get()
        .load(url)
        .placeholder(placeholder)
        .into(this)
}