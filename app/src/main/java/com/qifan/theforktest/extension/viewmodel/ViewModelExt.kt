package com.qifan.theforktest.extension.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


inline fun <reified T : ViewModel> FragmentActivity.getInjectViewModel(factory: ViewModelProvider.Factory) =
    ViewModelProvider(this, factory).get(T::class.java)

inline fun <reified T : ViewModel> Fragment.getInjectViewModel(factory: ViewModelProvider.Factory) =
    ViewModelProvider(this, factory).get(T::class.java)

inline fun <reified T : ViewModel> Fragment.getSharedInjectViewModel(factory: ViewModelProvider.Factory) =
    activity?.run {
        getInjectViewModel<T>(factory)
    } ?: throw IllegalStateException("Activity do not have this viewModel please to verify")