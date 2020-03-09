package com.qifan.theforktest.restaurant

import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.model.base.Results
import com.qifan.domain.model.exception.TheForkException
import com.qifan.domain.usecase.detail.GetRestaurantDetailUseCase
import com.qifan.theforktest.ui.restaurant.RestaurantDetailViewModel
import com.qifan.theforktest.utils.RxImmediateSchedulerRule
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RestaurantDetailViewModelTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var getRestaurantDetailUseCase: GetRestaurantDetailUseCase

    @Mock
    private lateinit var mockResult: Results<RestaurantModel>

    private var detailViewModel: RestaurantDetailViewModel? = null


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailViewModel = RestaurantDetailViewModel(getRestaurantDetailUseCase)
    }

    @After
    fun tearDown() {
        detailViewModel = null
    }

    @Test
    fun when_viewModel_should_call_use_case() {
        Mockito.`when`(getRestaurantDetailUseCase.getRestaurantDetail(ArgumentMatchers.anyString()))
            .thenReturn(
                Single.just(mockResult)
            )
        detailViewModel?.getDetail(ArgumentMatchers.anyString())
        Mockito.verify(getRestaurantDetailUseCase)
    }


    @Test
    fun when_use_case_return_success_view_model_should_have_state_success() {
        Mockito.`when`(getRestaurantDetailUseCase.getRestaurantDetail(ArgumentMatchers.anyString()))
            .thenReturn(
                Single.just(Results.success(buildRestaurantModel()))
            )
        val testObserver = TestObserver<Results<RestaurantModel>>()
        detailViewModel?.getDetail(ArgumentMatchers.anyString())
            ?.subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValue {
            it is Results.Success
        }
    }


    @Test
    fun when_use_case_return_success_view_model_should_have_state_fail() {
        Mockito.`when`(getRestaurantDetailUseCase.getRestaurantDetail(ArgumentMatchers.anyString()))
            .thenReturn(
                Single.just(Results.failure(TheForkException.NetworkException()))
            )
        val testObserver = TestObserver<Results<RestaurantModel>>()
        detailViewModel?.getDetail(ArgumentMatchers.anyString())
            ?.subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValue {
            it is Results.Failure
        }
    }

    @Test
    fun when_use_case_return_success_view_model_should_invoke_reactive_loading_state() {
        Mockito.`when`(getRestaurantDetailUseCase.getRestaurantDetail(ArgumentMatchers.anyString()))
            .thenReturn(
                Single.just(mockResult)
            )
        val testObserver = TestObserver<Results<RestaurantModel>>()
        detailViewModel?.getDetail(ArgumentMatchers.anyString())
            ?.subscribe(testObserver)

        val testObserverLoadingState = TestObserver<Boolean>()
        detailViewModel?.restaurantDetail
            ?.loading
            ?.toObservable()
            ?.subscribe(testObserverLoadingState)

        testObserverLoadingState.assertNoErrors()
    }


    private fun buildRestaurantModel(): RestaurantModel {
        return RestaurantModel(
            id = "id",
            name = "name",
            bannerUrl = null,
            foodType = null,
            slide = null,
            rateCount = null,
            rateDistinction = null,
            chefName = null,
            avgRate = null,
            avgPrice = null,
            tripAdvisorAverageRate = null,
            tripAdvisorReviewCount = null,
            startMenu1 = null,
            startMenu2 = null,
            startMenu3 = null,
            mainMenu1 = null,
            mainMenu2 = null,
            mainMenu3 = null,
            dessertMenu1 = null,
            dessertMenu2 = null,
            dessertMenu3 = null
        )
    }
}