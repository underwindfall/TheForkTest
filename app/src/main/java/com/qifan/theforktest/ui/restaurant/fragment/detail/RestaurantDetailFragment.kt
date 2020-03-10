package com.qifan.theforktest.ui.restaurant.fragment.detail

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.model.base.Results
import com.qifan.domain.model.exception.TheForkException
import com.qifan.theforktest.R
import com.qifan.theforktest.di.viewmodel.ViewModelFactory
import com.qifan.theforktest.extension.*
import com.qifan.theforktest.extension.reactive.mainThread
import com.qifan.theforktest.extension.reactive.subscribeAndLogError
import com.qifan.theforktest.extension.viewmodel.getSharedInjectViewModel
import com.qifan.theforktest.ui.base.view.fragment.InjectionFragment
import com.qifan.theforktest.ui.model.backDrawableResource
import com.qifan.theforktest.ui.notifier.ErrorListener
import com.qifan.theforktest.ui.notifier.ErrorNotifier
import com.qifan.theforktest.ui.notifier.notifier
import com.qifan.theforktest.ui.restaurant.RestaurantDetailViewModel
import io.reactivex.Flowable
import kotlinx.android.synthetic.main.detail_info_header.*
import kotlinx.android.synthetic.main.detail_info_menu.*
import kotlinx.android.synthetic.main.detail_note_rate.*
import kotlinx.android.synthetic.main.detail_note_tripadvisor_rate.*
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject


class RestaurantDetailFragment : InjectionFragment(), ErrorNotifier {
    override val errorListener: ErrorListener by notifier()

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    private lateinit var detailViewModel: RestaurantDetailViewModel


    private lateinit var slideAdapter: RestaurantSlideAdapter

    override fun getLayoutId(): Int = R.layout.fragment_detail

    override fun getMenuId(): Int? = R.menu.menu_search

    override fun onAttach(context: Context) {
        super.onAttach(context)
        detailViewModel = getSharedInjectViewModel(viewModelFactory)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupViewPager()
    }

    override fun onStart() {
        super.onStart()
        compositeDisposable.addAll(
            getRestaurantDetailLoading().subscribeAndLogError(),
            getRestaurantDetailSuccess().subscribeAndLogError(),
            getRestaurantDetailFailed().subscribeAndLogError()
        )

    }


    private fun getRestaurantDetailSuccess(): Flowable<Results<RestaurantModel>> {
        return detailViewModel.restaurantDetail
            .success
            .mainThread()
            .doOnNext { result ->
                when (result) {
                    is Results.Failure -> displayError(hasError = true, error = result.error)
                    is Results.Success -> bindRestaurant(result.data)
                }
            }
    }

    private fun getRestaurantDetailFailed(): Flowable<Pair<Boolean, Throwable?>> =
        detailViewModel.restaurantDetail
            .hasError
            .mainThread()
            .doOnNext { (hasError, error) ->
                displayError(hasError, error)
            }

    private fun displayError(hasError: Boolean, error: Throwable?) {
        if (hasError) {
            when (error) {
                is TheForkException.NetworkException -> Toast.makeText(
                    context,
                    resources.getString(R.string.search_network_error),
                    Toast.LENGTH_LONG
                ).show()
                is TheForkException.EmptyException -> Toast.makeText(
                    context,
                    resources.getString(R.string.search_empty_restaurant),
                    Toast.LENGTH_LONG
                ).show()
                else -> errorListener.showError(error)
            }
        }
    }

    private fun getRestaurantDetailLoading(): Flowable<Boolean> =
        detailViewModel.restaurantDetail
            .loading
            .mainThread()
            .doOnNext { show ->
                if (show) {
                    loading.visibility = View.VISIBLE
                    content_group.visibility = View.GONE
                } else {
                    loading.visibility = View.GONE
                    content_group.visibility = View.VISIBLE
                }
            }

    private fun bindRestaurant(data: RestaurantModel) {
        displaySlide(data)
        displayRestaurantInfo(data)
        displayRestaurantMenu(data)
        displayNoteRate(data)
        displayTripAdvisorNoteRate(data)
    }

    private fun displayTripAdvisorNoteRate(data: RestaurantModel) {
        with(data) {
            ifSomeNull(avgRate, rateCount, rateDistinction) {
                trip_advisor_container.visibility = View.GONE
                return
            }
            checkNullOrHideView(trip_advisor_rating, tripAdvisorAverageRate) {
                trip_advisor_rating.rating = it
            }
            checkNullOrHideView(trip_advisor_review_count, tripAdvisorReviewCount) {
                trip_advisor_review_count.text = it
            }
        }
    }

    private fun displayNoteRate(data: RestaurantModel) {
        with(data) {
            ifSomeNull(avgRate, rateCount, rateDistinction) {
                restaurant_user_rate_container.visibility = View.GONE
                return
            }
            checkNullOrHideView(restaurant_note_rate, avgRate) {
                restaurant_note_rate.text = getFormattedAvgRate(it)
            }

            checkNullOrHideView(restaurant_note_rate_count, rateCount) {
                restaurant_note_rate_count.text =
                    resources.getString(R.string.restaurant_note_rate_count, it)
            }

            checkNullOrHideView(restaurant_note_rate_distinction, rateDistinction) {
                restaurant_note_rate_distinction.text = it
            }
        }
    }

    private fun displayRestaurantMenu(data: RestaurantModel) {
        with(data) {
            restaurant_menu_title_date.text =
                resources.getString(
                    R.string.restaurant_menu_date,
                    getCurrentDate()
                )
            restaurant_menu_subtitle.text =
                resources.getString(
                    R.string.restaurant_menu_subtitle,
                    chefName ?: "Inconnu"
                )
            displayStart(this)
            displayMain(this)
            displayDessert(this)
        }
    }

    private fun displayDessert(restaurantModel: RestaurantModel) {
        restaurantModel.apply {
            displayMenuItem(
                dessertMenu1?.name,
                dessertMenu1?.price,
                dessertMenu1?.currency?.symbol,
                restaurant_menu_dessert_1,
                restaurant_menu_dessert_1_price
            )
            displayMenuItem(
                dessertMenu2?.name,
                dessertMenu2?.price,
                dessertMenu2?.currency?.symbol,
                restaurant_menu_dessert_2,
                restaurant_menu_dessert_2_price
            )
            displayMenuItem(
                dessertMenu3?.name,
                dessertMenu3?.price,
                dessertMenu3?.currency?.symbol,
                restaurant_menu_dessert_3,
                restaurant_menu_dessert_3_price
            )
        }
    }

    private fun displayMain(restaurantModel: RestaurantModel) {
        restaurantModel.apply {
            displayMenuItem(
                mainMenu1?.name,
                mainMenu1?.price,
                mainMenu1?.currency?.symbol,
                restaurant_menu_main_1,
                restaurant_menu_main_1_price
            )
            displayMenuItem(
                mainMenu2?.name,
                mainMenu2?.price,
                mainMenu2?.currency?.symbol,
                restaurant_menu_main_2,
                restaurant_menu_main_2_price
            )
            displayMenuItem(
                mainMenu3?.name,
                mainMenu3?.price,
                mainMenu3?.currency?.symbol,
                restaurant_menu_main_3,
                restaurant_menu_main_3_price
            )
        }
    }

    private fun displayStart(restaurantModel: RestaurantModel) {
        restaurantModel.apply {
            displayMenuItem(
                startMenu1?.name,
                startMenu1?.price,
                startMenu1?.currency?.symbol,
                restaurant_menu_start_1,
                restaurant_menu_start_1_price
            )
            displayMenuItem(
                startMenu2?.name,
                startMenu2?.price,
                startMenu2?.currency?.symbol,
                restaurant_menu_start_2,
                restaurant_menu_start_2_price
            )
            displayMenuItem(
                startMenu3?.name,
                startMenu3?.price,
                startMenu3?.currency?.symbol,
                restaurant_menu_start_3,
                restaurant_menu_start_3_price
            )
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayMenuItem(
        name: String?,
        price: Float?,
        currency: String?,
        nameView: AppCompatTextView,
        priceView: AppCompatTextView
    ) {
        checkNullOrHideView(nameView, name) {
            nameView.text = it
        }
        checkNullOrHideView(priceView, price) {
            priceView.text = "${it}${currency}"
        }
    }

    private fun displayRestaurantInfo(data: RestaurantModel) {
        with(data) {
            speciality.text = foodType
            guardLet(
                avgPrice?.amount.toString(),
                avgPrice?.currency?.symbol
            ) { (amount, currency) ->
                avg_price.apply {
                    visibility = View.VISIBLE
                    text = String.format(
                        resources.getString(R.string.restaurant_avg_price),
                        amount,
                        currency
                    )
                }
            }
            checkNullOrHideView(rate_point, avgRate) {
                rate_point.text = getFormattedAvgRate(it)
            }

            checkNullOrHideView(rate_count, rateCount) {
                rate_count.text = resources.getString(R.string.restaurant_rate_number, it)
            }

            toolbar_layout.title = name
        }
    }

    private fun getFormattedAvgRate(rate: Double): SpannableString {
        return with(resources.getString(R.string.restaurant_avg_rate, rate.toString())) {
            SpannableString(this)
                .apply {
                    setSpan(RelativeSizeSpan(2f), 0, 3, 0)
                }
        }
    }

    private fun displaySlide(data: RestaurantModel) {
        with(data) {
            val slides = when {
                !slide.isNullOrEmpty() -> slide!!
                !bannerUrl.isNullOrEmpty() -> listOf(RestaurantModel.Slide(url = bannerUrl!!))
                else -> emptyList()
            }
            slideAdapter.setData(slides)
        }
    }


    private fun setupToolbar() {
        activity?.apply {
            toolbar.apply {
                showNavIcon(context, backDrawableResource) { navigationIcon = it }
            }
            (this as AppCompatActivity).setSupportActionBar(toolbar)
            toolbar.setNavigationOnClickListener {
                hideNavIcon(this, backDrawableResource) { toolbar.navigationIcon = it }
                goBack()
            }
        }

    }

    private fun setupViewPager() {
        vp_restaurant_image.apply {
            slideAdapter = RestaurantSlideAdapter()
            adapter = slideAdapter
        }
    }

    private fun goBack() {
        activity?.onBackPressed()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.menu_action_search).setOnMenuItemClickListener {
            goBack()
            true
        }
    }

}