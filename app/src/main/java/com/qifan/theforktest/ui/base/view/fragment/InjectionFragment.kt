package com.qifan.theforktest.ui.base.view.fragment

import android.content.Context
import androidx.annotation.CallSuper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class InjectionFragment : BaseFragment(), HasAndroidInjector {
    @Inject
    protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    final override fun androidInjector(): AndroidInjector<Any> = androidInjector

    @CallSuper
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}