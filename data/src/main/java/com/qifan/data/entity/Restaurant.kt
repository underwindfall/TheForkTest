package com.qifan.data.entity

import com.google.gson.annotations.SerializedName
import com.qifan.domain.model.RestaurantModel

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
    val name: String
)

fun Restaurant.toModel(): RestaurantModel {
    return RestaurantModel(
        id = data.id.toString(),
        name = data.name
    )
}