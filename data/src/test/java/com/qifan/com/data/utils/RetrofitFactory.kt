package com.qifan.com.data.utils

import com.qifan.data.entity.ErrorType
import com.qifan.data.entity.Restaurant
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

fun getSuccessRestaurant(errorType: ErrorType? = null): Response<Restaurant> {
    return Response.success(buildRestaurant(errorType))
}


fun getHttpFailRestaurant(): Response<Restaurant> {
    return Response.error(
        400,
        """
           {
    "result": 0,
    "result_code": "RESTAURANT_NOT_FOUND",
    "result_detail": "\nmethod:restaurant_get_info\nkey:IPHONEPRODEDCRFV\nparams:{\"id_restaurant\":\"1\"}",
    "result_msg": "No restaurant found for id 1",
    "result_cached": "none",
    "data": [],
    "sync": []
}
        """.trimIndent()
            .toResponseBody("application/json".toMediaTypeOrNull())
    )
}