package com.qifan.theforktest.ui.notifier

import androidx.fragment.app.Fragment
import com.qifan.theforktest.ui.provider.WeakReferenceProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

interface ErrorNotifier {
    val errorListener: ErrorListener

    fun dispatchError(error: Throwable) {
        errorListener.showError(error)
    }

    fun onReactiveError(error: Throwable): Unit = dispatchError(error)
}


fun notifier(): ReadOnlyProperty<Fragment, ErrorListener> = ErrorFragmentNotifierProvider()

private class ErrorFragmentNotifierProvider : ReadOnlyProperty<Fragment, ErrorListener>,
    ErrorNotifier {
    override var errorListener: ErrorListener by WeakReferenceProvider()

    override fun getValue(thisRef: Fragment, property: KProperty<*>): ErrorListener {
        val activity = thisRef.activity
        check(activity is ErrorListener) { "Activity should implements ErrorListener." }
        this.errorListener = activity
        return errorListener
    }
}
