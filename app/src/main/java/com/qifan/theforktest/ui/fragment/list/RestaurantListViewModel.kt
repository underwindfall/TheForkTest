package com.qifan.theforktest.ui.fragment.list

import androidx.lifecycle.ViewModel
import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.usecase.detail.GetRestaurantDetailUseCase
import io.reactivex.Single
import javax.inject.Inject

class RestaurantListViewModel @Inject constructor(
    private val getRestaurantDetailUseCase: GetRestaurantDetailUseCase
) : ViewModel() {
    fun getDetail(): Single<RestaurantModel> {
        return getRestaurantDetailUseCase.getRestaurantDetail()
    }
}