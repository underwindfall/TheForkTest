package com.qifan.theforktest.extension.reactive

import android.os.Looper
import android.view.View
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable
import io.reactivex.disposables.Disposables

fun View.clicks(): Observable<Unit> {
    return ViewClickObservable(this)
}

private fun checkMainThread(observer: Observer<*>): Boolean {
    if (Looper.myLooper() != Looper.getMainLooper()) {
        observer.onSubscribe(Disposables.empty())
        observer.onError(
            IllegalStateException(
                "Expected to be called on the main thread but was " + Thread.currentThread().name
            )
        )
        return false
    }
    return true
}

private class ViewClickObservable(
    private val view: View
) : Observable<Unit>() {

    override fun subscribeActual(observer: Observer<in Unit>) {
        if (!checkMainThread(observer)) {
            return
        }
        val listener = Listener(view, observer)
        observer.onSubscribe(listener)
        view.setOnClickListener(listener)
    }

    private class Listener(
        private val view: View,
        private val observer: Observer<in Unit>
    ) : MainThreadDisposable(), View.OnClickListener {

        override fun onClick(v: View) {
            if (!isDisposed) {
                observer.onNext(Unit)
            }
        }

        override fun onDispose() {
            view.setOnClickListener(null)
        }
    }
}

