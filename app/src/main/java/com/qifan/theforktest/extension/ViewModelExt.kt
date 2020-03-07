package com.qifan.theforktest.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


inline fun <reified T : ViewModel> Fragment.getInjectViewModel(factory: ViewModelProvider.Factory) =
    ViewModelProvider(this, factory).get(T::class.java)
