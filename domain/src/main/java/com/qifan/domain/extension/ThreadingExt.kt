package com.qifan.domain.extension

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

fun <T> Flowable<T>.computation(): Flowable<T> {
    return this@computation
        .subscribeOn(Schedulers.computation())
}

fun <T> Single<T>.computation(): Single<T> {
    return this@computation
        .subscribeOn(Schedulers.computation())
}

fun <T> Flowable<T>.compute(): Flowable<T> {
    return this@compute
        .observeOn(Schedulers.computation())
}

fun <T> Single<T>.compute(): Single<T> {
    return this@compute
        .observeOn(Schedulers.computation())
}

fun <T> Flowable<T>.io(): Flowable<T> {
    return this@io
        .subscribeOn(Schedulers.io())
}

fun <T> Single<T>.io(): Single<T> {
    return this@io
        .subscribeOn(Schedulers.io())
}