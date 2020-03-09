package com.qifan.theforktest.di.module.interceptor

import com.qifan.theforktest.interceptor.HandleErrorInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
object InterceptorModule {
    @Provides
    @JvmStatic
    @Singleton
    fun provideHandleErrorInterceptor(): Interceptor {
        return HandleErrorInterceptor()
    }
}