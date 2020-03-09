package com.qifan.data.extension.rx.transfomer

import com.qifan.data.entity.Restaurant
import com.qifan.data.extension.processApiResponse
import com.qifan.data.mapper.toModel
import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.model.base.Results
import io.reactivex.Single
import io.reactivex.SingleTransformer

fun restaurantResponseToModelResult(): SingleTransformer<Restaurant, Results<RestaurantModel>> {
    return SingleTransformer { upstream: Single<Restaurant> ->
        upstream.flatMap { response ->
            when (val restaurant = processApiResponse(response)) {
                is Results.Success -> Single.just(Results.success(result = restaurant.data.toModel()))
                is Results.Failure -> Single.just(Results.failure(error = restaurant.error))
            }
        }
    }
}
