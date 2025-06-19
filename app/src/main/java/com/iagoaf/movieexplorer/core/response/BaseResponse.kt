package com.iagoaf.movieexplorer.core.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val results: T,
)