package com.qifan.data.entity

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

enum class ErrorType(val message: String? = null) {
    RESTAURANT_NOT_FOUND("RESTAURANT_NOT_FOUND"),
    BAD_PARAMETER("BAD_PARAMETER"),
    JSON_PARSING_EXCEPTION("JSON_PARSING_EXCEPTION"),
    UNKNOWN();

    companion object {
        @JvmStatic
        fun fromValue(value: String?): ErrorType? =
            values().find { it.message.equals(value, ignoreCase = true) }
    }
}

interface DefaultResponse {
    val result: Int?
    val resultCode: String?
    var errorType: ErrorType?

    fun isSuccess(): Boolean = result == RESULT_OK

    fun generateFailure(inputResultCode: String? = resultCode) {
        errorType = ErrorType.fromValue(inputResultCode)
    }

    companion object {
        const val RESULT_OK = 1

        fun <T : DefaultResponse> fromJson(
            resultCode: String?,
            responseType: Type
        ): T? {
            return emptyResponse<T>(responseType)?.apply {
                generateFailure(resultCode)
            }
        }

        fun <T : DefaultResponse?> emptyResponse(responseType: Type?): T? {
            var instance: T? = null
            try {
                val typeToken: TypeToken<T> = TypeToken.get(responseType) as TypeToken<T>
                val clazz: Class<in T> = typeToken.rawType
                instance = clazz.newInstance() as T
            } catch (cce: InstantiationException) {
            } catch (cce: IllegalAccessException) {
            } catch (cce: ClassCastException) {
            }
            if (instance == null) {
                instance = Gson().fromJson("{}", responseType)
            }
            instance?.generateFailure()
            return instance
        }
    }
}


data class Restaurant(
    @SerializedName("result")
    override val result: Int?,
    @SerializedName("result_code")
    override val resultCode: String?,
    @SerializedName("data")
    val data: RestaurantDetail,
    override var errorType: ErrorType? = null
) : DefaultResponse

data class RestaurantDetail(
    @SerializedName(value = "id_restaurant")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("pics_main")
    val banner: RestaurantSlide?,
    @SerializedName("pics_diaporama")
    val slide: List<RestaurantSlide>?,
    @SerializedName("speciality")
    val speciality: String?,
    @SerializedName("card_price")
    val price: Int?,
    @SerializedName("currency_code")
    val currencyCode: String?,
    @SerializedName("rate_count")
    val rateCount: Long?,
    @SerializedName("chef_name")
    val chefName: String?,
    @SerializedName("rate_distinction")
    val rateDistinction: String?,
    @SerializedName("trip_advisor_avg_rating")
    val tripAdvisorAverageRate: Float?,
    @SerializedName("trip_advisor_review_count")
    val tripAdvisorReviewCount: Int?,
    @SerializedName("avg_rate")
    val avgRate: Double?,
    @SerializedName("card_start_1")
    val menuStart1: String?,
    @SerializedName("card_start_2")
    val menuStart2: String?,
    @SerializedName("card_start_3")
    val menuStart3: String?,
    @SerializedName("card_main_1")
    val menuMain1: String?,
    @SerializedName("card_main_2")
    val menuMain2: String?,
    @SerializedName("card_main_3")
    val menuMain3: String?,
    @SerializedName("card_dessert_1")
    val menuDessert1: String?,
    @SerializedName("card_dessert_2")
    val menuDessert2: String?,
    @SerializedName("card_dessert_3")
    val menuDessert3: String?,
    @SerializedName("price_card_start_1")
    val priceStart1: Float?,
    @SerializedName("price_card_start_2")
    val priceStart2: Float?,
    @SerializedName("price_card_start_3")
    val priceStart3: Float?,
    @SerializedName("price_card_main_1")
    val priceMain1: Float?,
    @SerializedName("price_card_main_2")
    val priceMain2: Float?,
    @SerializedName("price_card_main_3")
    val priceMain3: Float?,
    @SerializedName("price_card_dessert_1")
    val priceDessert1: Float?,
    @SerializedName("price_card_dessert_2")
    val priceDessert2: Float?,
    @SerializedName("price_card_dessert_3")
    val priceDessert3: Float?
) {
    data class RestaurantSlide(
        @SerializedName("612x344")
        val url: String,
        @SerializedName("label")
        val label: String?
    )
}
