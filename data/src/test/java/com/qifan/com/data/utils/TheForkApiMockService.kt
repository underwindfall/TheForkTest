package com.qifan.com.data.utils

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class TheForkApiMockService<T>(private val c: Class<T>) {
    private val mockServer: MockWebServer = MockWebServer()

    private val okHttp = OkHttpClient.Builder()
        .build()

    private val gson = GsonBuilder()
        .serializeNulls()
        .create()
    private val forkRetrofit = Retrofit.Builder()
        .baseUrl(mockServer.url("/"))
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


    fun getMockServer() = mockServer

    fun get(): T {
        return forkRetrofit.create(c)
    }

    fun enqueueResponse(code: Int, file: String? = null) {
        val mockResponse = MockResponse()
        if (file != null) {
            mockResponse.setBody(getJsonFromFile(file))
        }
        mockServer.enqueue(mockResponse.setResponseCode(code))
    }

    fun takeRequest() = mockServer.takeRequest()

    private fun getJsonFromFile(file: String): String {
        val inputStreamResponse = this.javaClass.classLoader.getResourceAsStream(file)!!
        val size: Int
        return try {
            size = inputStreamResponse.available()
            val buffer = ByteArray(size)
            inputStreamResponse.read(buffer)
            inputStreamResponse.close()
            String(buffer)
        } catch (e: IOException) {
            throw RuntimeException()
        }
    }

}