package com.qifan.theforktest.extension.reactive

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers


fun <T> Flowable<T>.mainThread(): Flowable<T> {
    return this@mainThread
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.mainThread(): Single<T> {
    return this@mainThread
        .observeOn(AndroidSchedulers.mainThread())
}
