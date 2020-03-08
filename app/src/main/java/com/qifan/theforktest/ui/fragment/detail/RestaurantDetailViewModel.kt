package com.qifan.theforktest.ui.fragment.detail

import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.respository.RestaurantId
import com.qifan.domain.usecase.detail.GetRestaurantDetailUseCase
import com.qifan.theforktest.ui.base.viewmodel.BaseViewModel
import com.qifan.theforktest.ui.model.ReactiveLoadingState
import com.qifan.theforktest.ui.model.bindLoadingState
import io.reactivex.Single
import javax.inject.Inject

class RestaurantDetailViewModel @Inject constructor(
    private val getRestaurantDetailUseCase: GetRestaurantDetailUseCase
) : BaseViewModel() {
    val restaurantDetail: ReactiveLoadingState<RestaurantModel> =
        ReactiveLoadingState()

    fun getDetail(id: RestaurantId): Single<RestaurantModel> {
        return getRestaurantDetailUseCase.getRestaurantDetail(id)
            .bindLoadingState(restaurantDetail)
    }
}