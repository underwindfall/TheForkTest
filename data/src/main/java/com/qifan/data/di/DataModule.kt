package com.qifan.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.qifan.data.TheForkApi
import com.qifan.data.di.repository.RepositoryModule
import com.qifan.data.di.service.ServiceModule
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(
    includes = [
        ServiceModule::class,
        RepositoryModule::class
    ]
)
object DataModule {

    @Provides
    @JvmStatic
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @JvmStatic
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .create()
    }

    @Provides
    @JvmStatic
    @Singleton
    internal fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @JvmStatic
    @Singleton
    internal fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    @Provides
    @JvmStatic
    @Singleton
    internal fun provideRetrofit(
        okHttpClient: OkHttpClient,
        factory: RxJava2CallAdapterFactory,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(factory)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .baseUrl(TheForkApi.ENDPOINT)
            .build()
    }

}