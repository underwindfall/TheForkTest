package com.qifan.domain.extension

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

fun <T> Observable<T>.toFlowableDefault(): Flowable<T> = toFlowable(BackpressureStrategy.LATEST)

fun <T> Flowable<T>.toSingleOrError(): Single<T> = take(1).singleOrError()