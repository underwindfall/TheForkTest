package com.qifan.theforktest.ui.model

import com.qifan.domain.extension.computation
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.processors.BehaviorProcessor

class ReactiveLoadingState<T> {
    private val stateSink: BehaviorProcessor<State<T>> =
        BehaviorProcessor.createDefault(State(Status.INIT))

    private val state = stateSink
        .computation()

    val loading: Flowable<Boolean> = state
        .map { it.status == Status.LOADING }
        .distinctUntilChanged()

    val hasError: Flowable<Pair<Boolean, Throwable?>> = state
        .map { (it.status == Status.ERROR) to (it.error) }
        .distinctUntilChanged()

    val error: Flowable<Pair<Boolean, Throwable?>> = hasError
        .filter { (hasError, _) -> hasError }

    val success: Flowable<T> = state
        .map { (it.status == Status.SUCCESS) to it.data }
        .distinctUntilChanged()
        .filter { (isSuccess, data) -> isSuccess && data != null }
        .map { (_, data) -> data!! }

    fun onSuccess(data: T) = stateSink.onNext(State(Status.SUCCESS, data, null))
    fun onError(throwable: Throwable) = stateSink.onNext(State(Status.ERROR, null, throwable))
    fun onLoading() = stateSink.onNext(State(Status.LOADING, null, null))
    fun reset() = stateSink.onNext(State(Status.INIT))

    enum class Status {
        INIT,
        LOADING,
        SUCCESS,
        ERROR
    }

    data class State<T>(
        val status: Status,
        val data: T? = null,
        val error: Throwable? = null
    )
}

fun <T> Single<T>.bindLoadingState(asyncOperationState: ReactiveLoadingState<T>): Single<T> =
    doOnSubscribe { asyncOperationState.onLoading() }
        .doOnSuccess { asyncOperationState.onSuccess(it) }
        .doOnError { asyncOperationState.onError(it) }