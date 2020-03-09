package com.qifan.theforktest.di.component

import android.app.Application
import com.qifan.data.di.DataModule
import com.qifan.domain.di.DomainModule
import com.qifan.theforktest.App
import com.qifan.theforktest.di.module.ActivityBindModule
import com.qifan.theforktest.di.module.AppModule
import com.qifan.theforktest.di.module.interceptor.InterceptorModule
import com.qifan.theforktest.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBindModule::class,
        AppModule::class,
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class,
        InterceptorModule::class
    ]
)
interface AppComponent {
    fun inject(app: App)

    @Component.Factory
    interface Builder {
        fun create(@BindsInstance application: Application): AppComponent
    }
}