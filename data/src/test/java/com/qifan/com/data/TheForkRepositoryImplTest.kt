package com.qifan.com.data

import com.qifan.com.data.utils.buildRestaurant
import com.qifan.data.TheForkRepositoryImpl
import com.qifan.data.api.TheForkService
import com.qifan.data.entity.ErrorType
import com.qifan.data.entity.Restaurant
import com.qifan.data.entity.RestaurantDetail
import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.model.base.Results
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TheForkRepositoryImplTest {
    private val FAKE_ID = "FAKE_ID"

    @Mock
    private lateinit var mockService: TheForkService

    private lateinit var theForkRepositoryImpl: TheForkRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        theForkRepositoryImpl = TheForkRepositoryImpl(mockService)
    }

    @Test
    fun test_when_service_get_restaurant_detail_success() {
        given(mockService.getRestaurantDetail(restaurantId = FAKE_ID))
            .willReturn(Single.just(buildRestaurant()))
        val testObserver = TestObserver<Results<RestaurantModel>>()
        theForkRepositoryImpl.getRestaurantDetail(FAKE_ID)
            .subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValue { it is Results.Success }
    }


    @Test
    fun test_when_service_get_restaurant_detail_failed() {
        given(mockService.getRestaurantDetail(restaurantId = FAKE_ID))
            .willReturn(Single.just(buildRestaurant(ErrorType.RESTAURANT_NOT_FOUND)))
        val testObserver = TestObserver<Results<RestaurantModel>>()
        theForkRepositoryImpl.getRestaurantDetail(FAKE_ID)
            .subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValue { it is Results.Failure }
    }


}