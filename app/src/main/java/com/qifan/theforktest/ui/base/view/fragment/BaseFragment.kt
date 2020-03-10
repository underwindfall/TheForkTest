package com.qifan.theforktest.ui.base.view.fragment

import android.os.Bundle
import android.view.*
import androidx.annotation.CallSuper
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

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMenuId()?.run { setHasOptionsMenu(true) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        getMenuId()?.run { inflater.inflate(this, menu) }
    }

    abstract fun getMenuId(): Int?

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
}