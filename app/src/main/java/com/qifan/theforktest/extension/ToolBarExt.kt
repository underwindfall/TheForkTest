package com.qifan.theforktest.extension

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.qifan.theforktest.ui.model.ReverseAnimatedDrawableResource

internal typealias DrawableProviderCallback = (Drawable?) -> Unit

 inline fun hideNavIcon(
    context: Context,
    drawableResource: ReverseAnimatedDrawableResource,
    crossinline callback: DrawableProviderCallback
) {
    context.getAnimatedVectorDrawableCompat(
        drawableResource.idResOut
    )?.apply {
        this.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                super.onAnimationEnd(drawable)
                callback(null)
            }

        })
        callback(this)
        start()
    }
}

 inline fun showNavIcon(
    context: Context,
    drawableResource: ReverseAnimatedDrawableResource,
    callback: DrawableProviderCallback
) {
    context.getAnimatedVectorDrawableCompat(
        drawableResource.idResIn
    )?.apply {
        callback(this)
        start()
    }
}