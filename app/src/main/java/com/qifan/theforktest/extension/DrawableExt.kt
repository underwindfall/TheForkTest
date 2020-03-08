package com.qifan.theforktest.extension

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat

fun Context.getDrawableCompat(idRes: Int): Drawable? = ResourcesCompat.getDrawable(resources, idRes, theme)

fun Context.getAnimatedVectorDrawableCompat(idRes: Int): AnimatedVectorDrawableCompat? = AnimatedVectorDrawableCompat.create(this, idRes)