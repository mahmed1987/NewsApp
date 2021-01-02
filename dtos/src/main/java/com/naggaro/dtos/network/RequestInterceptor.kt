package com.naggaro.dtos.network

import okhttp3.Headers
import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 */
class RequestInterceptor(private val requestHeaders: RequestHeaders) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val builder = original
            .newBuilder()
            .headers(requestHeaders.headers.toHeaders())
            .method(original.method, original.body)
        val newRequest: Request = builder.build()
        return chain.proceed(newRequest)
    }
}