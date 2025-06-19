package com.iagoaf.movieexplorer.core.result

sealed class BaseResult<T> {
    data class Success<T>(val data: T, val totalPages: Int) : BaseResult<T>()
    data class Error<T>(val message: String) : BaseResult<T>()

    inline fun onSuccess(action: (data: T, totalPages: Int) -> Unit): BaseResult<T> {
        if (this is Success) action(data, totalPages)
        return this
    }

    inline fun onError(action: (String) -> Unit): BaseResult<T> {
        if (this is Error) action(message)
        return this
    }
}