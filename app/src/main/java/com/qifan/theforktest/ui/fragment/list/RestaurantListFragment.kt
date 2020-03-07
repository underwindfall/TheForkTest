package com.qifan.theforktest.ui.fragment.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.qifan.domain.model.RestaurantModel
import com.qifan.theforktest.R
import com.qifan.theforktest.di.viewmodel.ViewModelFactory
import com.qifan.theforktest.extension.getInjectViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RestaurantListFragment : Fragment(), HasAndroidInjector {
    @Inject
    internal lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    private lateinit var restaurantListViewModel: RestaurantListViewModel

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        restaurantListViewModel = getInjectViewModel(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

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


    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }

}