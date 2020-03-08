package com.qifan.theforktest.ui.fragment.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.qifan.domain.model.RestaurantBannerModel
import com.qifan.domain.respository.RestaurantId
import com.qifan.theforktest.R
import com.qifan.theforktest.di.viewmodel.ViewModelFactory
import com.qifan.theforktest.extension.reactive.mainThread
import com.qifan.theforktest.extension.reactive.subscribeAndLogError
import com.qifan.theforktest.extension.reactive.subscribeError
import com.qifan.theforktest.extension.viewmodel.getInjectViewModel
import com.qifan.theforktest.ui.base.view.fragment.InjectionFragment
import com.qifan.theforktest.ui.decorator.MarginItemDecorator
import com.qifan.theforktest.ui.notifier.ErrorListener
import com.qifan.theforktest.ui.notifier.ErrorNotifier
import com.qifan.theforktest.ui.notifier.notifier
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class RestaurantListFragment : InjectionFragment(), ErrorNotifier {

    override val errorListener: ErrorListener by notifier()

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    private lateinit var restaurantListViewModel: RestaurantListViewModel

    private lateinit var restaurantListAdapter: RestaurantListAdapter

    private val demoRestaurantListId: List<RestaurantId> =
        listOf("40370", "16409", "14163", "40171")

    private val routeCallback by lazy<RouteCallBack> {
        activity.let {
            check(it is RouteCallBack) { "Activity should implements RouteCallBack" }
            it
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        restaurantListViewModel = getInjectViewModel(viewModelFactory)
    }

    override fun getLayoutId(): Int = R.layout.fragment_list


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkRouteCallback()
    }

    private fun checkRouteCallback() {
        routeCallback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setUpToolbar()
        compositeDisposable.addAll(
            getRestaurantList().subscribeError(this),
            getRestaurantListSuccess().subscribeAndLogError(),
            getRestaurantListFailed().subscribeAndLogError(),
            getRestaurantListLoading().subscribeAndLogError(),
            handleTransactionSelected().subscribeAndLogError()
        )
    }

    private fun setUpToolbar() {
        activity?.apply {
            toolbar.apply {
                title = "The Mock Fork List"
            }
        }
    }

    private fun setupRecyclerView() {
        rv_restaurants.apply {
            layoutManager = GridLayoutManager(context, 2).apply {
                with(
                    MarginItemDecorator(
                        MarginItemDecorator.Space(
                            space = resources.getDimension(R.dimen.list_gap_default).toInt()
                        )
                    )
                ) {
                    addItemDecoration(this)
                }
            }

            restaurantListAdapter = RestaurantListAdapter()
            adapter = restaurantListAdapter
        }
    }


    private fun getRestaurantList(): Single<List<RestaurantBannerModel>> {
        return restaurantListViewModel.getListRestaurant(demoRestaurantListId)
            .mainThread()
    }

    private fun handleTransactionSelected() = restaurantListAdapter
        .onItemSelected
        .flatMapCompletable { navigateToDetail(it) }


    private fun navigateToDetail(id: RestaurantId): Completable = Completable.fromCallable {
        routeCallback.navigateToDetail(id)
    }


    private fun getRestaurantListSuccess(): Flowable<List<RestaurantBannerModel>> =
        restaurantListViewModel.restaurantBanners
            .success
            .mainThread()
            .doOnNext(::bindRestaurantBanner)


    private fun bindRestaurantBanner(data: List<RestaurantBannerModel>) {
        restaurantListAdapter.setData(data)
    }


    private fun getRestaurantListFailed(): Flowable<Pair<Boolean, Throwable?>> =
        restaurantListViewModel.restaurantBanners
            .hasError
            .mainThread()
            .doOnNext { (hasError, error) ->
                if (hasError) {
                    errorListener.showError(error)
                }
            }


    private fun getRestaurantListLoading(): Flowable<Boolean> =
        restaurantListViewModel.restaurantBanners
            .loading
            .mainThread()
            .doOnNext { show ->
                if (show) {
                    loading.visibility = View.VISIBLE
                    rv_restaurants.visibility = View.GONE
                } else {
                    loading.visibility = View.GONE
                    rv_restaurants.visibility = View.VISIBLE
                }
            }

}