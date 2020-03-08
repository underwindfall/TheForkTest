package com.qifan.theforktest.ui.model

import androidx.annotation.DrawableRes
import com.qifan.theforktest.R

data class ReverseAnimatedDrawableResource(
    @DrawableRes val idResIn: Int,
    @DrawableRes val idResOut: Int,
    @DrawableRes val idResDefault: Int
)

val backDrawableResource by lazy {
    ReverseAnimatedDrawableResource(
        R.drawable.ic_back_enter_animated,
        R.drawable.ic_back_leave_animated,
        R.drawable.ic_back_out
    )
}