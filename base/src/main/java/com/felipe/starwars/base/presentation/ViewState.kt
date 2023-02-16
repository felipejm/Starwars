package com.felipe.starwars.base.presentation

sealed class ViewState<T> {
    data class Error<T>(val exception: Throwable) : ViewState<T>()
    data class Success<T>(val value: T) : ViewState<T>()
    class Empty<T> : ViewState<T>()
    class Loading<T> : ViewState<T>()
}
