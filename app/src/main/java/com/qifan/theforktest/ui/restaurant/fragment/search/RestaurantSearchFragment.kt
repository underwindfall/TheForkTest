package com.qifan.theforktest.ui.restaurant.fragment.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.qifan.domain.extension.toFlowableDefault
import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.model.exception.TheForkException
import com.qifan.domain.respository.RestaurantId
import com.qifan.theforktest.R
import com.qifan.theforktest.di.viewmodel.ViewModelFactory
import com.qifan.theforktest.extension.afterTextChanged
import com.qifan.theforktest.extension.reactive.*
import com.qifan.theforktest.extension.viewmodel.getSharedInjectViewModel
import com.qifan.theforktest.ui.base.view.fragment.InjectionFragment
import com.qifan.theforktest.ui.notifier.ErrorListener
import com.qifan.theforktest.ui.notifier.ErrorNotifier
import com.qifan.theforktest.ui.notifier.notifier
import com.qifan.theforktest.ui.restaurant.RestaurantDetailViewModel
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class RestaurantSearchFragment : InjectionFragment(), ErrorNotifier {
    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    override val errorListener: ErrorListener by notifier()

    private lateinit var detailViewModel: RestaurantDetailViewModel

    private val onSearchId = PublishProcessor.create<RestaurantId>()

    private val routeCallback by lazy<RouteCallBack> {
        activity.let {
            check(it is RouteCallBack) { "Activity should implements RouteCallBack" }
            it
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_search

    override fun onAttach(context: Context) {
        super.onAttach(context)
        detailViewModel = getSharedInjectViewModel(viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkRouteCallback()
    }

    private fun checkRouteCallback() {
        routeCallback
    }

    override fun onStart() {
        super.onStart()
        handleButtonClick().subscribeAndLogError()
            .let(compositeDisposable::add)
        getRestaurantDetailLoading().subscribeAndLogError()
            .let(compositeDisposable::add)
        getRestaurantDetailFailed().subscribeError(this)
            .let(compositeDisposable::add)
        getRestaurantDetailSuccess().subscribeAndLogError()
            .let(compositeDisposable::add)
        getRestaurantDetails().subscribeAndLogError()
            .let(compositeDisposable::add)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        setUpInputView()
    }

    private fun setUpInputView() {
        search_edit_text.afterTextChanged {
            if (search_error.visibility == View.VISIBLE && search_button.visibility == View.GONE) {
                search_error.visibility = View.GONE
                search_button.visibility = View.VISIBLE
            }
        }
    }


    private fun handleButtonClick(): Flowable<Unit> = search_button.clicks()
        .toFlowableDefault()
        .throttleDefault()
        .doOnNext { buttonAction() }


    private fun buttonAction() {
        val id = search_edit_text.text.toString()
        search_button.setOnClickListener {
            when {
                id.isEmpty() -> {
                    Toast.makeText(
                        context,
                        resources.getString(R.string.search_tooltip_empty),
                        Toast.LENGTH_LONG
                    ).show()
                }
                "[0-9]+".toRegex().matches(id) -> {
                    onSearchId.onNext(id)
                }
                else -> {
                    Toast.makeText(
                        context,
                        resources.getString(R.string.search_tooltip_invalid),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }

    private fun setUpToolbar() {
        activity?.apply {
            toolbar.apply {
                title = "Recherche Une Restaurant"
            }
        }
    }

    private fun getRestaurantDetailSuccess(): Flowable<RestaurantModel> {
        return detailViewModel.restaurantDetail
            .success
            .mainThread()
            .doOnNext {
                routeCallback.navigateToDetail()
            }
    }


    private fun getRestaurantDetailFailed(): Flowable<Pair<Boolean, Throwable?>> =
        detailViewModel.restaurantDetail
            .hasError
            .mainThread()
            .doOnNext { (hasError, error) ->
                if (hasError) {
                    when (error) {
                        is TheForkException.NetworkException,
                        is TheForkException.EmptyException -> {
                            search_error.visibility = View.VISIBLE
                            search_button.visibility = View.GONE
                        }
                        else -> errorListener.showError(error)
                    }
                } else {
                    search_error.visibility = View.GONE
                    search_button.visibility = View.VISIBLE
                }
            }

    private fun getRestaurantDetailLoading(): Flowable<Boolean> =
        detailViewModel.restaurantDetail
            .loading
            .mainThread()
            .doOnNext { show ->
                if (show) {
                    search_loading.visibility = View.VISIBLE
                    search_button.visibility = View.GONE
                } else {
                    search_loading.visibility = View.GONE
                    search_button.visibility = View.VISIBLE
                }
            }

    private fun getRestaurantDetails(): Flowable<RestaurantModel> {
        return onSearchId.flatMapSingle { detailViewModel.getDetail(it) }
            .mainThread()
    }

}