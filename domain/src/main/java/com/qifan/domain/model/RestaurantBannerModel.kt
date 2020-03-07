package com.qifan.domain.model

import com.qifan.domain.respository.RestaurantId
import java.util.*

data class RestaurantBannerModel(
    val id: RestaurantId,
    val name: String,
    val bannerUrl: String?,
    val speciality: String?,
    val price: Price?,
    val averageRate: Double?
) {
    data class Price(val amount: Int, val currency: Currency)
}