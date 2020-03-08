package com.qifan.theforktest.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


fun ViewGroup.inflateLayout(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

inline fun <reified T : Any> checkNullOrHideView(
    view: View,
    parameter: T?,
    showFunc: (parameter: T) -> Unit
) {
    parameter?.run {
        showFunc(this)
        view.visibility = View.VISIBLE
    } ?: view.apply { visibility = View.GONE }
}