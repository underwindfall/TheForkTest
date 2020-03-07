package com.qifan.domain

import io.reactivex.Single

interface GetRestaurantDetailUseCase : UseCase {
    fun getRestaurantDetail(): Single<RestaurantModel>
}

class GetRestaurantDetailUseCaseImpl(
    private val theForkRepository: TheForkRepository
) : GetRestaurantDetailUseCase {
    override fun getRestaurantDetail(): Single<RestaurantModel> {
        return theForkRepository.getRestaurantDetail()
    }
}