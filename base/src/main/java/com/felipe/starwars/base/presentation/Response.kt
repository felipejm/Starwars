package com.felipe.starwars.base.presentation

sealed class Response<T> {
    data class Success<T>(val value: T) : Response<T>()
    data class Error<T>(val error: Throwable) : Response<T>()
}
