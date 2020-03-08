package com.qifan.domain.model

import com.qifan.domain.respository.RestaurantId
import java.util.*

data class RestaurantModel(
    val id: RestaurantId,
    val name: String,
    val bannerUrl: String?,
    val foodType: String?,
    val slide: List<Slide>?,
    val rateCount: String?,
    val rateDistinction: String?,
    val chefName: String?,
    val avgPrice: AvgPrice?,
    val tripAdvisorAverageRate: Float?,
    val tripAdvisorReviewCount: String?,
    val avgRate: Double?,
    val startMenu1: MenuItem?,
    val startMenu2: MenuItem?,
    val startMenu3: MenuItem?,
    val mainMenu1: MenuItem?,
    val mainMenu2: MenuItem?,
    val mainMenu3: MenuItem?,
    val dessertMenu1: MenuItem?,
    val dessertMenu2: MenuItem?,
    val dessertMenu3: MenuItem?
) {
    data class Slide(
        val url: String,
        val label: String? = null
    )

    data class AvgPrice(
        val amount: Int,
        val currency: Currency
    )

    data class MenuItem(
        val name: String?,
        val price: Float?,
        val currency: Currency
    )
}