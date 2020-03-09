package com.qifan.data.api.service

import com.qifan.data.entity.DefaultResponse
import com.qifan.data.entity.ErrorType
import io.reactivex.Single
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

interface ForkServiceManager {
    fun <T : DefaultResponse> manageHttpResponse(
        call: Single<Response<T>>,
        response: Response<T>,
        responseType: Type
    ): Single<T>
}

@Singleton
class ForkServiceManagerImpl @Inject constructor() : ForkServiceManager {
    override fun <T : DefaultResponse> manageHttpResponse(
        call: Single<Response<T>>,
        response: Response<T>,
        responseType: Type
    ): Single<T> {
        val finalResponse: T?
        if (!response.isSuccessful) {
            finalResponse = try {
                DefaultResponse.fromJson(ErrorType.JSON_PARSING_EXCEPTION.message, responseType)
            } catch (e: Exception) {
                DefaultResponse.emptyResponse(responseType)
            }
        } else {
            finalResponse = response.body()
            finalResponse?.apply {
                if (!isSuccess()) {
                    generateFailure()
                }
            }
        }

        return Single.just(finalResponse)
    }

}


