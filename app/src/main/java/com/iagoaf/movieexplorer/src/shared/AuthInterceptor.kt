package com.iagoaf.movieexplorer.src.shared

import com.iagoaf.movieexplorer.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val movieAuthToken = BuildConfig.API_KEY
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", movieAuthToken)
            .build()
        return chain.proceed(request)
    }
}