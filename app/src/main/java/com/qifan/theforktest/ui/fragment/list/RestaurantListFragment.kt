package com.qifan.theforktest.ui.fragment.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.qifan.domain.model.RestaurantModel
import com.qifan.theforktest.R
import com.qifan.theforktest.di.viewmodel.ViewModelFactory
import com.qifan.theforktest.extension.viewmodel.getInjectViewModel
import com.qifan.theforktest.ui.base.view.fragment.InjectionFragment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class RestaurantListFragment : InjectionFragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    private lateinit var restaurantListViewModel: RestaurantListViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        restaurantListViewModel = getInjectViewModel(viewModelFactory)
    }

    override fun getLayoutId(): Int = R.layout.fragment_list


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compositeDisposable.add(
            getRestaurantList().subscribe { it ->
                Log.d(
                    "Qifan", """
                ==================
                ${it.id}
                ${it.name}
                ==================
            """.trimIndent()
                )
            }
        )
    }


    private fun getRestaurantList(): Single<RestaurantModel> {
        return restaurantListViewModel.getDetail()
            .observeOn(AndroidSchedulers.mainThread())
    }

}