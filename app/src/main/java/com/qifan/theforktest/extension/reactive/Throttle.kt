package com.qifan.theforktest.extension.reactive

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.TimeUnit

const val THROTTLE_DURATION = 1L

fun <T> Flowable<T>.throttleDefault(): Flowable<T> {
    return this@throttleDefault
        .throttleFirst(THROTTLE_DURATION, TimeUnit.SECONDS)
}

