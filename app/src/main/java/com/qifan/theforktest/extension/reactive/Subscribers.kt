package com.qifan.theforktest.extension.reactive

import android.util.Log
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.Disposable


fun <T> Flowable<T>.subscribeError(): Disposable = subscribe({}, { e ->
    logStreamError(e)
})

fun <T> Single<T>.subscribeError(): Disposable = subscribe({}, { e ->
    logStreamError(e)
})

fun <T> Flowable<T>.subscribeAndLogError(): Disposable = subscribe({}, ::logStreamError)

fun <T> Single<T>.subscribeAndLogError(): Disposable = subscribe({}, ::logStreamError)

fun <T> Flowable<T>.logError(): Flowable<T> = doOnError(::logStreamError)

fun <T> Single<T>.logError(): Single<T> = doOnError(::logStreamError)

private fun logStreamError(exception: Throwable) {
    Log.e("StreamError", exception.message, exception)
}