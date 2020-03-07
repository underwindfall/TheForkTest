package com.qifan.data.entity

import com.google.gson.annotations.SerializedName
import com.qifan.domain.model.RestaurantBannerModel
import com.qifan.domain.model.RestaurantModel
import java.util.*

data class Restaurant(
    @SerializedName("result")
    val result: Int,
    @SerializedName("result_code")
    val resultCode: String,
    @SerializedName("data")
    val data: RestaurantDetail
)

data class RestaurantDetail(
    @SerializedName(value = "id_restaurant")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("pics_main")
    var banner: RestaurantSlide? = null,
    @SerializedName("pics_diaporama")
    var slide: List<RestaurantSlide>? = null,
    @SerializedName("speciality")
    var speciality: String? = null,
    @SerializedName("card_price")
    var price: Int? = null,
    @SerializedName("currency_code")
    var currencyCode: String? = null,
    @SerializedName("avg_rate")
    var averageRate: Float? = null,
    @SerializedName("rate_count")
    var rateCount: Long? = null,
    @SerializedName("rate_distinction")
    var rateDistinction: String? = null,
    @SerializedName("trip_advisor_avg_rating")
    var tripAdvisorAverageRate: Double? = null,
    @SerializedName("trip_advisor_review_count")
    var tripAdvisorReviewCount: Int? = null
) {
    data class RestaurantSlide(
        @SerializedName("612x344")
        val url: String,
        @SerializedName("label")
        val label: String? = null
    )
}


fun Restaurant.toModel(): RestaurantModel {
    return RestaurantModel(
        id = data.id.toString(),
        name = data.name
    )
}

fun Restaurant.toBannerModel(): RestaurantBannerModel {
    return with(data) {
        RestaurantBannerModel(
            id = id.toString(),
            name = name,
            bannerUrl = banner?.url,
            speciality = speciality,
            price = if (price != null && currencyCode != null) RestaurantBannerModel.Price(
                price!!,
                Currency.getInstance(currencyCode)
            ) else null,
            averageRate = averageRate?.toDouble()
        )
    }
}