package com.qifan.domain.usecase.detail

import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.model.base.Results
import com.qifan.domain.respository.RestaurantId
import com.qifan.domain.usecase.UseCase
import io.reactivex.Single

interface GetRestaurantDetailUseCase : UseCase {
    fun getRestaurantDetail(id: RestaurantId): Single<Results<RestaurantModel>>
}
