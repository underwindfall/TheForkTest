package com.qifan.theforktest.interceptor

import android.util.Log
import com.qifan.data.entity.DefaultResponse
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import javax.inject.Inject

class HandleErrorInterceptor @Inject constructor() : ResponseBodyInterceptor() {
    override fun intercept(response: Response, url: String, body: String): Response {
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(body)
            if (jsonObject.optInt("result") != DefaultResponse.RESULT_OK) {
                jsonObject.put("data", null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val contentType = response.body?.contentType()
        val responseBody = jsonObject.toString().toResponseBody(contentType)
        return response.newBuilder().body(responseBody).build()
    }

}