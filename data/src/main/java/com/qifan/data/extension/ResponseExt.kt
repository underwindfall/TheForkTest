package com.qifan.data.extension

import com.qifan.data.entity.DefaultResponse
import com.qifan.data.entity.ErrorType
import com.qifan.domain.model.base.Results
import com.qifan.domain.model.exception.TheForkException

fun <T : DefaultResponse> processApiResponse(response: T): Results<T> {
    return response.errorType?.let {
        when (it) {
            ErrorType.RESTAURANT_NOT_FOUND -> Results.failure<T>(TheForkException.EmptyException())
            else -> Results.failure(TheForkException.NetworkException())
        }
    } ?: Results.success(response)

}