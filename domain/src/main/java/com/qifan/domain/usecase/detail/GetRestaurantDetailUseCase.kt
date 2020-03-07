package com.qifan.domain.usecase.detail

import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.usecase.UseCase
import io.reactivex.Single

interface GetRestaurantDetailUseCase : UseCase {
    fun getRestaurantDetail(): Single<RestaurantModel>
}
