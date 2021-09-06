package com.avrioc.assessment.ui.main.utils

sealed class DataResult<T> (
    val data: T? = null,
    val message: String? = null,
    val isLoading: Boolean = false
){
    class Success<T>(data: T) : DataResult<T>(data)

    class Error<T>(message: String?) : DataResult<T>(message = message)

    class Loading<T>(isLoading: Boolean) : DataResult<T>(isLoading = isLoading)
}
