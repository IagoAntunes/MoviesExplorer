package com.iagoaf.movieexplorer.src.shared

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhNDA0YTQyMmU0Y2FiODU5ZjJlNzdhYTdhNDAwYjlhYiIsIm5iZiI6MTczMzIzNDc4My4yNCwic3ViIjoiNjc0ZjEwNWZiMjA5ZTAxZWFhODk5NzQ2Iiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.QFyk3micHRNAW4XM0e6O6YoZZF-YYjH_akv_Cg0ihr0")
            .build()
        return chain.proceed(request)
    }
}