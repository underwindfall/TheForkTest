package com.qifan.data.extension

import com.qifan.domain.model.base.Results
import com.qifan.domain.model.exception.TheForkException
import retrofit2.Response
import java.io.IOException

fun <T> processApiResponse(response: Response<T>): Results<T> {
    return try {
        val responseCode = response.code()
        val responseMessage = response.message()
        if (response.isSuccessful) {
            Results.success(response.body()!!)
        } else {
            val errorMessage =
                "responseCode ======> $responseCode responseMessage =====>$responseMessage "
            Results.failure(TheForkException.NetworkException(errorMessage))
        }
    } catch (e: IOException) {
        Results.failure(TheForkException.NetworkException())
    }
}