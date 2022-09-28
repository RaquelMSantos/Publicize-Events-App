package com.example.publicizeeventsapp.data.retrofit

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

const val CACHE_CONTROL_HEADER = "Cache-Control"
const val MAX_AGE = 10

class EventsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val cacheControl = CacheControl.Builder()
            .maxAge(MAX_AGE, TimeUnit.MINUTES)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .build()

        return chain.proceed(newRequest)
            .newBuilder()
            .header(CACHE_CONTROL_HEADER, cacheControl.toString())
            .build()
    }
}
