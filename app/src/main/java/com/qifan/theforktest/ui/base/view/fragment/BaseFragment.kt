package com.qifan.theforktest.ui.base.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.qifan.theforktest.extension.inflateLayout
import com.qifan.theforktest.ui.base.view.IView
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment(), IView {

    protected val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflateLayout(getLayoutId())
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
}