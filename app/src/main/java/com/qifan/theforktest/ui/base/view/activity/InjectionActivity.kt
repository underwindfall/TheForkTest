package com.qifan.theforktest.ui.base.view.activity

import android.os.Bundle
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class InjectionActivity : BaseActivity(), HasAndroidInjector {

    @Inject
    protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    final override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}