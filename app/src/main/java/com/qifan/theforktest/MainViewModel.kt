package com.qifan.theforktest

import com.qifan.data.TheForkRepositoryImpl
import com.qifan.domain.GetRestaurantDetailUseCase
import com.qifan.domain.GetRestaurantDetailUseCaseImpl
import com.qifan.domain.RestaurantModel
import io.reactivex.Single

class MainViewModel(
    private val getRestaurantDetailUseCase: GetRestaurantDetailUseCase = GetRestaurantDetailUseCaseImpl(
        TheForkRepositoryImpl()
    )
) {
    fun getDetail(): Single<RestaurantModel> {
        return getRestaurantDetailUseCase.getRestaurantDetail()
    }
}