package com.example.newsapp.data.repository

sealed class CustomResponse<T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val errorCode: Int? = null
) {
    class Loading<T> : CustomResponse<T>()
    class Success<T>(data: T? = null) : CustomResponse<T>(data = data)
    class Error<T>(errorMessage: String, errorCode: Int? = null) :
        CustomResponse<T>(errorMessage = errorMessage, errorCode = errorCode)
}
