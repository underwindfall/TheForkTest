package com.qifan.theforktest.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


fun ViewGroup.inflateLayout(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}